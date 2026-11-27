package com.example.decide.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.decide.R
import com.example.decide.ui.theme.AppTheme

@Composable
fun ShowHomeScreen(
    decisions: List<String>,
    onTitleSubmitted: (String) -> Unit,
    onDecisionSelected: (String) -> Unit,
    onDecisionDeleted: (String) -> Unit,
    selectedTheme: AppTheme,
    onThemeSelected: (AppTheme) -> Unit,
) {
    var decisionTitle by rememberSaveable { mutableStateOf("") }
    var themeMenuExpanded by rememberSaveable { mutableStateOf(false) }
    val selectedThemeName = stringResource(
        when (selectedTheme) {
            AppTheme.LIGHT -> R.string.theme_light
            AppTheme.DARK -> R.string.theme_dark
            AppTheme.CREAM -> R.string.theme_cream
            AppTheme.NAVY -> R.string.theme_navy
        },
    )

    fun submitTitle() {
        val title = decisionTitle.trim()
        if (title.isNotEmpty()) {
            onTitleSubmitted(title)
            decisionTitle = ""
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            text = stringResource(R.string.decision_title_prompt),
            style = MaterialTheme.typography.headlineSmall,
        )

        OutlinedTextField(
            value = decisionTitle,
            onValueChange = { decisionTitle = it },
            label = { Text(stringResource(R.string.decision_title_label)) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { submitTitle() }),
        )

        Box(modifier = Modifier.fillMaxWidth()) {
            OutlinedButton(
                onClick = { themeMenuExpanded = true },
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(stringResource(R.string.theme_button, selectedThemeName))
            }

            DropdownMenu(
                expanded = themeMenuExpanded,
                onDismissRequest = { themeMenuExpanded = false },
            ) {
                DropdownMenuItem(
                    text = { Text(stringResource(R.string.theme_light)) },
                    onClick = {
                        onThemeSelected(AppTheme.LIGHT)
                        themeMenuExpanded = false
                    },
                )
                DropdownMenuItem(
                    text = { Text(stringResource(R.string.theme_dark)) },
                    onClick = {
                        onThemeSelected(AppTheme.DARK)
                        themeMenuExpanded = false
                    },
                )
                DropdownMenuItem(
                    text = { Text(stringResource(R.string.theme_cream)) },
                    onClick = {
                        onThemeSelected(AppTheme.CREAM)
                        themeMenuExpanded = false
                    },
                )
                DropdownMenuItem(
                    text = { Text(stringResource(R.string.theme_navy)) },
                    onClick = {
                        onThemeSelected(AppTheme.NAVY)
                        themeMenuExpanded = false
                    },
                )
            }
        }

        if (decisions.isEmpty()) {
            Spacer(modifier = Modifier.weight(1f))
        } else {
            Text(
                text = stringResource(R.string.saved_decisions_title),
                style = MaterialTheme.typography.titleLarge,
            )

            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(decisions) { decision ->
                    OutlinedCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onDecisionSelected(decision) },
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                text = decision,
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(16.dp),
                                style = MaterialTheme.typography.bodyLarge,
                            )

                            IconButton(
                                onClick = { onDecisionDeleted(decision) },
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = stringResource(R.string.delete_decision),
                                    tint = MaterialTheme.colorScheme.error,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
