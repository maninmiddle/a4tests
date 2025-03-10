package com.maninmiddle.features.test_solve.domain.model

data class VariantModel(
    val id: Int,
    val taskId: Int,
    val text: String,
    val isCorrect: Boolean
)
