package com.maninmiddle.core.common.network.dto

import com.squareup.moshi.Json

data class TaskItemDto(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "testId") val testId: Int,
    @field:Json(name = "type") val type: String,
    @field:Json(name = "text") val text: String,
    @field:Json(name = "variants") val variants: List<VariantItemDto>?,
    @field:Json(name = "correct_answer") val correctAnswer: String
)