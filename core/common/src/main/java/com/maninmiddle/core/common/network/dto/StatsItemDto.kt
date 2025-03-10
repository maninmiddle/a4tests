package com.maninmiddle.core.common.network.dto

import com.squareup.moshi.Json

data class StatsItemDto(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "testId") val testId: Int,
    @field:Json(name = "rightAnswers") val rightAnswers: Int,
    @field:Json(name = "count_of_answers") val countOfAnswers: Int
)