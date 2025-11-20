package com.example.quotesapp_challenge3

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.quotesapp_challenge3.ui.QuoteUiState

@Composable
fun QuoteScreen(
    viewModel: QuoteViewModel = viewModel()
) {
    val state = viewModel.uiState
    val softPink = Color(0xFFF6EDEA)   // fondo tipo Figma

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = softPink
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Barra superior blanca con el título centrado
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(vertical = 20.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Quote",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF111111)
                )
            }

            // Zona rosa con la tarjeta
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(softPink),
                contentAlignment = Alignment.Center
            ) {
                QuoteCard(
                    state = state,
                    onRefreshClick = { viewModel.loadRandomQuote() }
                )
            }
        }
    }
}
@Composable
fun QuoteCard(
    state: QuoteUiState,
    onRefreshClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(0.9f)             // que no ocupe el 100%, deja márgenes
            .wrapContentHeight(),
        shape = RoundedCornerShape(14.dp),  // bordes más suaves
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 18.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                when {
                    state.isLoading -> {
                        Text(
                            text = "Cargando frase...",
                            fontSize = 15.sp
                        )
                    }

                    state.errorMessage != null -> {
                        Text(
                            text = state.errorMessage,
                            color = MaterialTheme.colorScheme.error,
                            fontSize = 15.sp
                        )
                    }

                    else -> {
                        Text(
                            text = if (state.quoteText.isNotEmpty())
                                state.quoteText
                            else
                                "Tocá el botón para obtener una frase.",
                            fontSize = 18.sp,
                            lineHeight = 24.sp,
                            textAlign = TextAlign.Left,
                            color = Color(0xFF222222),
                            fontFamily = FontFamily.Serif   // 👈 estilo “libro”
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        if (state.author.isNotEmpty()) {
                            Text(
                                text = state.author,
                                fontSize = 13.sp,
                                color = Color(0xFF666666),
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }

            IconButton(
                onClick = onRefreshClick,
                modifier = Modifier.align(Alignment.BottomEnd)
            ) {
                Icon(
                    imageVector = Icons.Filled.Refresh,
                    contentDescription = "Nueva frase",
                    tint = Color(0xFF111111)      // negro como en el Figma
                )
            }
        }
    }
}