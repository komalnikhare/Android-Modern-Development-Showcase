package com.example.composeapplication.flow.usecase3

import android.content.Context
import com.example.composeapplication.flow.mock.createFlowMockApi
import com.example.composeapplication.flow.mock.fakeCurrentStockPrices
import com.example.composeapplication.utils.MockNetworkInterceptor
import com.google.gson.Gson

fun mockApiUseCase3(context: Context) =
    createFlowMockApi(
        MockNetworkInterceptor()
            .mock(
                path = "/current-stock-prices",
                body = {Gson().toJson(fakeCurrentStockPrices(context))},
                status = 200,
                delayInMs = 1500,
                errorFrequencyInPercent = 50
            )
    )