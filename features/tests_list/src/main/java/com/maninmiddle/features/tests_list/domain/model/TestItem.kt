package com.maninmiddle.features.tests_list.domain.model

data class TestItem(
    val id: Int,
    val name: String,
    val subject: String,
    val completeTime: Int,
    val password: String,
)
