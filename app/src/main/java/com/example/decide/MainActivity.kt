package com.example.decide

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.example.decide.decision.ShowDecisionScreen
import com.example.decide.home.ShowHomeScreen
import com.example.decide.ui.theme.AppTheme
import com.example.decide.ui.theme.DecideTheme
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val systemInDarkTheme = isSystemInDarkTheme()
            var selectedTheme by rememberSaveable {
                mutableStateOf(
                    if (systemInDarkTheme) AppTheme.DARK else AppTheme.LIGHT,
                )
            }

            DecideTheme(appTheme = selectedTheme) {
                ShowDecideApp(
                    selectedTheme = selectedTheme,
                    onThemeSelected = { selectedTheme = it },
                )
            }
        }
    }
}

@Composable
private fun ShowDecideApp(
    selectedTheme: AppTheme,
    onThemeSelected: (AppTheme) -> Unit,
) {
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
            onDecisionDeleted = { title ->
                decisionTitles = decisionTitles - title
            },
            selectedTheme = selectedTheme,
            onThemeSelected = onThemeSelected,
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
