package com.example.composeapplication.flow.usecase1

import com.example.composeapplication.flow.mock.Stock

sealed class UiState {
    object Loading : UiState()
    data class Success(val stockList: List<Stock>) : UiState()
    data class Error(val message: String) : UiState()
}