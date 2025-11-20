package com.example.quotesapp_challenge3.data.remote

data class QuoteDto(
    val quote: String,
    val author: String,
    val work: String?,                 // lo podemos ignorar luego
    val categories: List<String>?      // también lo podemos ignorar
)