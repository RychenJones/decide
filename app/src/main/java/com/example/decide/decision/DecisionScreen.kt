package com.example.decide.decision

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.decide.R

@Composable
fun ShowDecisionScreen(
    title: String,
    onBack: () -> Unit,
    viewModel: DecisionViewModel = viewModel(key = title),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    var proText by rememberSaveable { mutableStateOf("") }
    var conText by rememberSaveable { mutableStateOf("") }
    var proSignificance by rememberSaveable { mutableStateOf(5) }
    var conSignificance by rememberSaveable { mutableStateOf(5) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        TextButton(
            onClick = onBack,
            contentPadding = PaddingValues(0.dp),
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.back_button),
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(stringResource(R.string.back_button))
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.weight(1f),
            )

            OutlinedButton(
                onClick = {
                    viewModel.resetDecision()
                    proText = ""
                    conText = ""
                    proSignificance = 5
                    conSignificance = 5
                },
            ) {
                Text(stringResource(R.string.reset_button))
            }
        }

        Text(
            text = stringResource(R.string.pros_entry_title),
            style = MaterialTheme.typography.titleLarge,
        )

        EnterScoredItem(
            text = proText,
            significance = proSignificance,
            onTextChange = { proText = it },
            onSignificanceChange = { proSignificance = it },
            onAdd = {
                viewModel.addPro(proText, proSignificance)
                proText = ""
            },
        )

        Text(
            text = stringResource(R.string.cons_entry_title),
            style = MaterialTheme.typography.titleLarge,
        )

        EnterScoredItem(
            text = conText,
            significance = conSignificance,
            onTextChange = { conText = it },
            onSignificanceChange = { conSignificance = it },
            onAdd = {
                viewModel.addCon(conText, conSignificance)
                conText = ""
            },
        )

        HorizontalDivider()

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.Top,
        ) {
            ListItems(
                title = stringResource(R.string.pros_title),
                items = uiState.pros,
                modifier = Modifier.weight(1f),
            )

            ListItems(
                title = stringResource(R.string.cons_title),
                items = uiState.cons,
                modifier = Modifier.weight(1f),
            )
        }

        HorizontalDivider()

        ShowConclusion(uiState)
    }
}

@Composable
private fun ShowConclusion(uiState: DecisionUiState) {
    val recommendationText = when (uiState.recommendation) {
        Recommendation.CONSIDER -> stringResource(R.string.recommend_consider)
        Recommendation.RECONSIDER -> stringResource(R.string.recommend_reconsider)
        Recommendation.NO_CLEAR_RECOMMENDATION -> {
            stringResource(R.string.recommend_no_clear_result)
        }
    }

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = stringResource(R.string.conclusion_title),
            style = MaterialTheme.typography.titleLarge,
        )

        Text(stringResource(R.string.total_pro_weight, uiState.totalProWeight))
        Text(stringResource(R.string.total_con_weight, uiState.totalConWeight))
        Text(stringResource(R.string.recommendation, recommendationText))
    }
}

@Composable
private fun EnterScoredItem(
    text: String,
    significance: Int,
    onTextChange: (String) -> Unit,
    onSignificanceChange: (Int) -> Unit,
    onAdd: () -> Unit,
) {
    var dropdownExpanded by rememberSaveable { mutableStateOf(false) }

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            OutlinedTextField(
                value = text,
                onValueChange = onTextChange,
                label = { Text(stringResource(R.string.item_text_label)) },
                modifier = Modifier.weight(1f),
                singleLine = true,
            )

            Box {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = stringResource(R.string.significance_label),
                        style = MaterialTheme.typography.labelSmall,
                    )

                    OutlinedButton(
                        onClick = { dropdownExpanded = true },
                        modifier = Modifier.width(88.dp),
                    ) {
                        Text(text = significance.toString())
                    }
                }

                DropdownMenu(
                    expanded = dropdownExpanded,
                    onDismissRequest = { dropdownExpanded = false },
                ) {
                    (1..10).forEach { value ->
                        DropdownMenuItem(
                            text = { Text(value.toString()) },
                            onClick = {
                                onSignificanceChange(value)
                                dropdownExpanded = false
                            },
                        )
                    }
                }
            }
        }

        Button(
            onClick = onAdd,
            enabled = text.isNotBlank(),
            modifier = Modifier.align(Alignment.End),
        ) {
            Text(stringResource(R.string.add_button))
        }
    }
}

@Composable
private fun ListItems(
    title: String,
    items: List<DecisionItem>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
        )

        if (items.isEmpty()) {
            Text(
                text = stringResource(R.string.no_items),
                style = MaterialTheme.typography.bodySmall,
            )
        } else {
            items.forEach { item ->
                Text(
                    text = "${item.text} (${item.significance}/10)",
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}
