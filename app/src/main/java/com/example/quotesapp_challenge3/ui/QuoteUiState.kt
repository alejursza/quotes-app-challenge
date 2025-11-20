package com.example.quotesapp_challenge3.ui

data class QuoteUiState(
    val isLoading: Boolean = false,
    val quoteText: String = "",
    val author: String = "",
    val errorMessage: String? = null
)