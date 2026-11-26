package com.example.decide.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.decide.R

@Composable
fun ShowHomeScreen(
    decisions: List<String>,
    onTitleSubmitted: (String) -> Unit,
) {
    var decisionTitle by rememberSaveable { mutableStateOf("") }

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
                    Text(
                        text = decision,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
            }
        }
    }
}
