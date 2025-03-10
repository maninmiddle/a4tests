package com.maninmiddle.features.test_solve.domain.model

data class TaskModel(
    val id: Int,
    val testId: Int,
    val type: String,
    val text: String,
    val variants: List<VariantModel>,
    val correctAnswer: String?
)