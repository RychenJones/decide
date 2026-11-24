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

    if (decisionTitle == null) {
        ShowHomeScreen(
            onTitleSubmitted = { title ->
                decisionTitle = title.toTitleCase()
            },
        )
    } else {
        ShowDecisionScreen(title = decisionTitle!!)
    }
}

private fun String.toTitleCase(): String {
    return lowercase(Locale.getDefault())
        .split(Regex("\\s+"))
        .joinToString(" ") { word ->
            word.replaceFirstChar { character -> character.titlecase() }
        }
}
