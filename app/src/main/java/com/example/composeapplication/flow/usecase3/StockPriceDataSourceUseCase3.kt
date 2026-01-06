package com.example.composeapplication.flow.usecase3

import android.util.Log
import com.example.composeapplication.flow.mock.FlowMockApi
import com.example.composeapplication.flow.mock.Stock
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retry
import retrofit2.HttpException

private const val Tag = "StockPriceDataSourceUseCase3"

interface StockPriceDataSourceUseCase3 {
    val latestStockList: Flow<List<Stock>>
}

class NetworkStockPriceDataSourceUseCase3(mockApi: FlowMockApi) : StockPriceDataSourceUseCase3 {

    override val latestStockList: Flow<List<Stock>> = flow {
        while (true){
            Log.d(Tag, "Fetching current stock prices")
            val currentStockList = mockApi.getCurrentStockPrices()
            emit(currentStockList)
            delay(5000)
        }
    }.retry { cause ->
        Log.d(Tag, "Enter retry operator with : $cause")
        val shouldRetry = cause is HttpException

        if (shouldRetry){
            delay(5000)
        }
        shouldRetry
    }
}