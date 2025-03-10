package com.maninmiddle.features.tests_list.domain.model

data class TaskModel(
    val id: Int,
    val testId: Int,
    val text: String,
    val type: String,
    val variants: List<VariantModel>
)