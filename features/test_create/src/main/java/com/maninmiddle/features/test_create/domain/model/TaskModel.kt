package com.maninmiddle.features.test_create.domain.model

data class TaskModel(
    val id: Int,
    val testId: Int,
    var type: String,
    var text: String,
    var variants: MutableList<VariantModel>,
    var correctAnswer: String?
)