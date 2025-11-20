package com.example.quotesapp_challenge3.data.remote

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.api-ninjas.com/"
private const val API_KEY = "uXK3rz8Q6SutGmXNaWqDjw==0Hg7jBEqpn3jfbrq"   // 👈 reemplazar

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request()
            .newBuilder()
            .addHeader("X-Api-Key", API_KEY)
            .build()
        return chain.proceed(newRequest)
    }
}

object QuotesApiService {

    private val client = OkHttpClient.Builder()
        .addInterceptor(ApiKeyInterceptor())
        .build()

    val api: QuotesApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuotesApi::class.java)
    }
}