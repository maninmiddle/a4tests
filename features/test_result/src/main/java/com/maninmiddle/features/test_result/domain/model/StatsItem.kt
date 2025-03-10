package com.maninmiddle.features.test_result.domain.model

data class StatsItem(
    val id: Int,
    val name: String,
    val testId: Int,
    val rightAnswers: Int,
    val countOfAnswers: Int
)