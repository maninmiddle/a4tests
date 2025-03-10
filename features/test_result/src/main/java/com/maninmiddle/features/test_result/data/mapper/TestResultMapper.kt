package com.maninmiddle.features.test_result.data.mapper

import com.maninmiddle.core.common.network.dto.StatsItemDto
import com.maninmiddle.features.test_result.domain.model.StatsItem

fun StatsItem.toStatsItemDto(): StatsItemDto {
    return StatsItemDto(
        id = id,
        name = name,
        testId = testId,
        rightAnswers = rightAnswers,
        countOfAnswers = countOfAnswers
    )
}