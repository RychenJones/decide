package com.example.decide.decision

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.Locale

enum class Recommendation {
    CONSIDER,
    RECONSIDER,
    NO_CLEAR_RECOMMENDATION,
}

data class DecisionUiState(
    val pros: List<DecisionItem> = emptyList(),
    val cons: List<DecisionItem> = emptyList(),
) {
    val totalProWeight: Int
        get() = pros.sumOf { it.significance }

    val totalConWeight: Int
        get() = cons.sumOf { it.significance }

    val recommendation: Recommendation
        get() = when {
            totalProWeight > totalConWeight -> Recommendation.CONSIDER
            totalConWeight > totalProWeight -> Recommendation.RECONSIDER
            else -> Recommendation.NO_CLEAR_RECOMMENDATION
        }
}

class DecisionViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(DecisionUiState())
    val uiState: StateFlow<DecisionUiState> = _uiState.asStateFlow()

    fun addPro(text: String, significance: Int) {
        val item = createItem(text, significance) ?: return
        _uiState.update { state ->
            state.copy(pros = state.pros + item)
        }
    }

    fun addCon(text: String, significance: Int) {
        val item = createItem(text, significance) ?: return
        _uiState.update { state ->
            state.copy(cons = state.cons + item)
        }
    }

    fun removePro(item: DecisionItem) {
        _uiState.update { state ->
            state.copy(pros = state.pros - item)
        }
    }

    fun removeCon(item: DecisionItem) {
        _uiState.update { state ->
            state.copy(cons = state.cons - item)
        }
    }

    fun resetDecision() {
        _uiState.value = DecisionUiState()
    }

    private fun createItem(text: String, significance: Int): DecisionItem? {
        val trimmedText = text.trim()
        if (trimmedText.isEmpty()) return null

        return DecisionItem(
            text = trimmedText
                .lowercase(Locale.getDefault())
                .replaceFirstChar { character -> character.titlecase() },
            significance = significance.coerceIn(1, 10),
        )
    }
}
