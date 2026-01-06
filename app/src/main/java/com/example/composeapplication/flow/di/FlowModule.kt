package com.example.composeapplication.flow.di

import com.example.composeapplication.flow.mock.FlowMockApi
import com.example.composeapplication.flow.mock.createFlowMockApi
import com.example.composeapplication.flow.usecase1.FlowUseCase1ViewModel
import com.example.composeapplication.flow.usecase1.NetworkStockPriceDataSource
import com.example.composeapplication.flow.usecase1.StockPriceDataSource
import com.example.composeapplication.flow.usecase1.mockApi
import com.example.composeapplication.flow.usecase2.FlowUseCase2ViewModel
import com.example.composeapplication.flow.usecase3.FlowUseCase3ViewModel
import com.example.composeapplication.flow.usecase3.NetworkStockPriceDataSourceUseCase3
import com.example.composeapplication.flow.usecase3.StockPriceDataSourceUseCase3
import com.example.composeapplication.flow.usecase3.mockApiUseCase3
import com.example.composeapplication.utils.MockNetworkInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.core.qualifier.qualifier
import org.koin.dsl.bind
import org.koin.dsl.module

val flowModule = module {

    single { MockNetworkInterceptor() }

    single<FlowMockApi>(named("defaultMock")) {
        mockApi(androidContext())
    }
    single<FlowMockApi>(named("useCase3Mock")) {
        mockApiUseCase3(androidContext())
    }
    single { NetworkStockPriceDataSource(get(qualifier = named("defaultMock")))
    } bind StockPriceDataSource::class
    single { NetworkStockPriceDataSourceUseCase3(get(qualifier = named("useCase3Mock")))
    } bind StockPriceDataSourceUseCase3::class
    viewModel { FlowUseCase1ViewModel(get()) }
    viewModel { FlowUseCase2ViewModel(get()) }
    viewModel { FlowUseCase3ViewModel(get()) }

}