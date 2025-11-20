package com.example.quotesapp_challenge3

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quotesapp_challenge3.ui.QuoteUiState
import com.example.quotesapp_challenge3.data.remote.QuotesApiService
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class QuoteViewModel : ViewModel() {

    // 👇 AHORA sí es un estado observable por Compose
    var uiState by mutableStateOf(QuoteUiState())
        private set

    fun loadRandomQuote(category: String? = null) {
        uiState = uiState.copy(
            isLoading = true,
            errorMessage = null
        )

        viewModelScope.launch {
            try {
                val quotes = QuotesApiService.api.getRandomQuote(category)
                val first = quotes.firstOrNull()

                uiState = if (first != null) {
                    QuoteUiState(
                        isLoading = false,
                        quoteText = "“${first.quote}”",
                        author = first.author,
                        errorMessage = null
                    )
                } else {
                    QuoteUiState(
                        isLoading = false,
                        quoteText = "",
                        author = "",
                        errorMessage = "No se encontró ninguna frase."
                    )
                }
            } catch (e: Exception) {
                uiState = uiState.copy(
                    isLoading = false,
                    errorMessage = "Error al cargar la frase."
                )
            }
        }
    }
}