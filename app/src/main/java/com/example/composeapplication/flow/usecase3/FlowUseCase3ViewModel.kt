package com.example.composeapplication.flow.usecase3

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.asLiveData
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import com.example.composeapplication.flow.usecase1.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.withIndex
import kotlinx.coroutines.launch

const val TAG = "FlowUseCase3ViewModel"
class FlowUseCase3ViewModel(
    stockPriceDataSource: StockPriceDataSourceUseCase3
): ViewModel() {

    /*
        Exercise: Flow Exception Handling

        Tasks:
        - Adjust code in StockPriceDataSource and FlowUseCase3ViewModel

        Exception Handling Goals:
        - for HttpExceptions in the datasource
            - re-collect from the flow
            - delay for 5 seconds before re-collecting the flow
        - for all other Exceptions within the whole flow pipeline
            - show toast with error message by emitting UiState.Error
     */


    val uiState: StateFlow<UiState> =
        stockPriceDataSource
            .latestStockList
            .map { stockList ->
                if (stockList.isNotEmpty()) UiState.Success(stockList)
                else UiState.Error("No Data")
            }
            .onStart {
                emit(UiState.Loading)
            }
            .onCompletion {
                Log.d(TAG, "Flow has completed.")
            }
            .catch { throwable ->
                Log.e(TAG, "Enter catch operator with: $throwable")
                emit(UiState.Error( "Something went wrong"))
            }
            //Perform all flow processing on a background thread
            .flowOn(Dispatchers.Default)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted
                    .WhileSubscribed(5000),
                initialValue = UiState.Loading
            )

}