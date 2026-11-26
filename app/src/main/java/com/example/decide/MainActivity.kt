package com.example.decide

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.example.decide.decision.ShowDecisionScreen
import com.example.decide.home.ShowHomeScreen
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                ShowDecideApp()
            }
        }
    }
}

@Composable
private fun ShowDecideApp() {
    var decisionTitle by rememberSaveable { mutableStateOf<String?>(null) }
    var decisionTitles by rememberSaveable { mutableStateOf(emptyList<String>()) }

    if (decisionTitle == null) {
        ShowHomeScreen(
            decisions = decisionTitles,
            onTitleSubmitted = { title ->
                val formattedTitle = title.toTitleCase()
                if (formattedTitle !in decisionTitles) {
                    decisionTitles = decisionTitles + formattedTitle
                }
                decisionTitle = formattedTitle
            },
            onDecisionSelected = { title ->
                decisionTitle = title
            },
        )
    } else {
        ShowDecisionScreen(
            title = decisionTitle!!,
            onBack = { decisionTitle = null },
        )
    }
}

private fun String.toTitleCase(): String {
    return lowercase(Locale.getDefault())
        .split(Regex("\\s+"))
        .joinToString(" ") { word ->
            word.replaceFirstChar { character -> character.titlecase() }
        }
}
