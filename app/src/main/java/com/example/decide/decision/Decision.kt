package com.example.decide.decision

data class DecisionItem(
    val text: String,
    val significance: Int,
)

data class Decision(
    val title: String,
    val pros: List<DecisionItem> = emptyList(),
    val cons: List<DecisionItem> = emptyList(),
)
