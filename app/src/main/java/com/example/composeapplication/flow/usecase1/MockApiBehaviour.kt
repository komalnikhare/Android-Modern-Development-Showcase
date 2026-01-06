package com.example.composeapplication.flow.usecase1

import android.content.Context
import com.example.composeapplication.flow.mock.createFlowMockApi
import com.example.composeapplication.flow.mock.fakeCurrentStockPrices
import com.example.composeapplication.utils.MockNetworkInterceptor
import com.google.gson.Gson

fun mockApi(context: Context) =
    createFlowMockApi(
        MockNetworkInterceptor()
            .mock(
                path = "/current-stock-prices",
                body = {Gson().toJson(fakeCurrentStockPrices(context))},
                status = 200,
                delayInMs = 1500
            )
    )