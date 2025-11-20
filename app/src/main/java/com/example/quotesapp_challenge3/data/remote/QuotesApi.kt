package com.example.quotesapp_challenge3.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface QuotesApi {

    // Random quote
    @GET("v2/randomquotes")
    suspend fun getRandomQuote(
        @Query("categories") categories: String? = null   // podés dejarlo null
    ): List<QuoteDto>
}