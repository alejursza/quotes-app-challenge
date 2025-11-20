package com.example.quotesapp_challenge3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.quotesapp_challenge3.ui.theme.QuotesAppChallenge3Theme
class MainActivity : ComponentActivity() {
    private val viewModel: QuoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuotesAppChallenge3Theme {
                QuoteScreen(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    QuotesAppChallenge3Theme {
        Greeting("Android")
    }
}