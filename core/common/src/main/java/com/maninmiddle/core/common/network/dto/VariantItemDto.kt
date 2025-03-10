package com.maninmiddle.core.common.network.dto

import com.squareup.moshi.Json

data class VariantItemDto(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "taskId") val taskId: Int,
    @field:Json(name = "text") val text: String,
    @field:Json(name = "is_correct") val isCorrect: Boolean
)