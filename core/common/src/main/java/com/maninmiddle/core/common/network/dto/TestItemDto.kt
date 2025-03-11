package com.maninmiddle.core.common.network.dto

import com.squareup.moshi.Json

data class TestItemDto(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "subject") val subject: String,
    @field:Json(name = "complete_time") val completeTime: Int,
    @field:Json(name = "password") val password: String,
)