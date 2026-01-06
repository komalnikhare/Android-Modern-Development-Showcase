package com.example.composeapplication.flow.usecase1

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.asLiveData
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

const val TAG = "FlowUseCase1ViewModel"
class FlowUseCase1ViewModel(
    stockPriceDataSource: StockPriceDataSource
): ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(
        UiState.Loading
    )
    val uiState: StateFlow<UiState> = _uiState

    init {
        // Here 2 different type of operator example use to emit data from Flow

        //1st operator here we have use collect
        /*viewModelScope.launch {
            stockPriceDataSource
            .latestStockList
            .collect{ stockList ->
                if (stockList.isNotEmpty()) _uiState.update { UiState.Success(stockList) }
                else _uiState.update { UiState.Error("No Data")}
            }
        }*/

        // 2nd operator here we have used launchIn()
        stockPriceDataSource
            .latestStockList
            .onStart { // This is optional. Used here to verify operator
               _uiState.update { UiState.Loading }
            }
            .onEach { stockList ->
                if (stockList.isNotEmpty()) _uiState.update { UiState.Success(stockList) }
                else _uiState.update { UiState.Error("No Data") }
            }
            .launchIn(viewModelScope)

    }

    //Here I am using another operator which is stateIn() which is used to convert Flow to StateFlow
   // with UI lifecycle awareness
    /*val uiState: StateFlow<UiState> =
        stockPriceDataSource
            .latestStockList
            .map { stockList ->
                if (stockList.isNotEmpty()) UiState.Success(stockList)
                else UiState.Error("No Data")
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted
                    .WhileSubscribed(5000),
                initialValue = UiState.Loading
            )*/

}