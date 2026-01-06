package com.example.composeapplication.compose

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeapplication.compose.data.ConferenceDataUiState
import com.example.composeapplication.compose.data.ConferenceRepository
import com.example.composeapplication.compose.data.getSelectedSession
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.example.composeapplication.R
import com.example.composeapplication.compose.data.Day
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlin.time.Duration.Companion.seconds

private const val TAG = "MainViewModel"

class MainViewModel(
    private val saveStateHandle: SavedStateHandle,
    private val conferenceRepository: ConferenceRepository
): ViewModel() {
    private val _uiState = MutableStateFlow<ConferenceDataUiState>(
        ConferenceDataUiState(isLoading = true)
    )
    val uiState: StateFlow<ConferenceDataUiState> = _uiState

    val selectedSession = saveStateHandle.getStateFlow<Int?>(
        "sessionId",null
    ).map { sessionId ->
        sessionId?.let { _uiState.value.getSelectedSession(sessionId) }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Companion.WhileSubscribed(500),
        initialValue = null
    )

    init {
        viewModelScope.launch {
            try {
                saveStateHandle.getStateFlow<Day>("day", initialValue = Day.Day1)
                    .collect{ day ->
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                sessionInfos = conferenceRepository.loadConferenceInfo(),
                                day = day
                            )
                        }
                    }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = R.string.unable_to_load_conference_data_error
                    )
                }
            }
        }
    }

    fun setSelectedSessionId(sessionId: Int){
        saveStateHandle["sessionId"] = sessionId
    }

    fun toggleFavorite(sessionId: Int){
        viewModelScope.launch {
            try {
                val favoriteIds = conferenceRepository.toggleFavorite(sessionId)
                _uiState.update {
                    val updatedSessionInfos = it.sessionInfos.map {
                        it.copy(isFavorite = favoriteIds.contains(it.session.id) )
                    }
                    it.copy(
                        sessionInfos = updatedSessionInfos,
                        snackbarMessage = R.string.save_remove_favorites_success
                    )
                }
            } catch (e: Exception){
                _uiState.update {
                    it.copy(
                        snackbarMessage = R.string.save_remove_favorites_error
                    )
                }
            }
        }
    }

    fun showSnackbar() = _uiState.update {
        it.copy(snackbarMessage = null)
    }
    fun setDay(day: Day){
        saveStateHandle["day"] = day
        _uiState.update { it.copy(day = day) }
    }
}