package com.maninmiddle.features.test_create.domain.model

data class VariantModel(
    val id: Int,
    val taskId: Int,
    var text: String,
    var isCorrect: Boolean
)
