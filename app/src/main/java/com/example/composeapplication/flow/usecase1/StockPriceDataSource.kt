package com.example.composeapplication.flow.usecase1

import android.util.Log
import com.example.composeapplication.flow.mock.FlowMockApi
import com.example.composeapplication.flow.mock.Stock
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

private const val Tag = "StockPriceDataSource"

interface StockPriceDataSource {
    val latestStockList: Flow<List<Stock>>
}

class NetworkStockPriceDataSource(mockApi: FlowMockApi) : StockPriceDataSource {

    override val latestStockList: Flow<List<Stock>> = flow {
        while (true){
            Log.d(Tag, "Fetching current stock prices")
            val currentStockList = mockApi.getCurrentStockPrices()
            emit(currentStockList)
            delay(5000)
        }
    }
}