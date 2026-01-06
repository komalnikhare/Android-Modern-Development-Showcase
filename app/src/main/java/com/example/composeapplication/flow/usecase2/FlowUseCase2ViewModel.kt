package com.example.composeapplication.flow.usecase2

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.asLiveData
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import com.example.composeapplication.flow.usecase1.StockPriceDataSource
import com.example.composeapplication.flow.usecase1.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.cancellable
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

const val TAG = "FlowUseCase2ViewModel"
class FlowUseCase2ViewModel(
    stockPriceDataSource: StockPriceDataSource
): ViewModel() {

    /*

    Flow exercise 1 Goals
        1) only update stock list when Alphabet(Google) (stock.name ="Alphabet (Google)") stock price is > 2300$
        2) only show stocks of "United States" (stock.country == "United States")
        3) show the correct rank (stock.rank) within "United States", not the world wide rank
        4) filter out Apple  (stock.name ="Apple") and Microsoft (stock.name ="Microsoft"), so that Google is number one
        5) only show company if it is one of the biggest 10 companies of the "United States" (stock.rank <= 10)
        6) stop flow collection after 10 emissions from the dataSource
        7) log out the number of the current emission so that we can check if flow collection stops after exactly 10 emissions
        8) Perform all flow processing on a background thread

     */

    val uiState: StateFlow<UiState> =
        stockPriceDataSource
            .latestStockList
            .take(10) //6) stop flow collection after 10 emissions from the dataSource
            .filter { stockList ->
                // 1) only update stock list when Alphabet(Google) (stock.name ="Alphabet (Google)") stock price is > 2300$
                val googleStock = stockList.find {
                    it.name == "Alphabet (Google)"
                }
                googleStock != null && googleStock.currentPrice > 2300
            }
            .map {
                // 2) only show stocks of "United States" (stock.country == "United States")
                it.filter { stock ->
                    stock.country == "United States"
                }
            }
            .cancellable() //get from cancellation example flow
            .map {
                //4) filter out Apple  (stock.name ="Apple") and Microsoft (stock.name ="Microsoft"), so that Google is number one
                it.filter { stock ->
                    stock.name != "Apple" && stock.name != "Microsoft"
                }
            }
            .map { stockList ->
                //3) show the correct rank (stock.rank) within "United States", not the world wide rank
                stockList.mapIndexed { index, stock ->
                    stock.copy(rank = index + 1)
                }
            }
            .map {
                //5) only show company if it is one of the biggest 10 companies of the "United States" (stock.rank <= 10)
                it.filter { stock ->
                    stock.rank <= 10
                }
            }
            .map { stockList ->
                if (stockList.isNotEmpty()) UiState.Success(stockList)
                else UiState.Error("No Data")
            }
            //8) Perform all flow processing on a background thread
            .flowOn(Dispatchers.Default)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted
                    .WhileSubscribed(5000),
                initialValue = UiState.Loading
            )

}