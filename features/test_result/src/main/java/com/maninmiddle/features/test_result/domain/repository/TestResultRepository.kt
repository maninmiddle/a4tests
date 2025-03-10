package com.maninmiddle.features.test_result.domain.repository

import com.maninmiddle.features.test_result.domain.model.StatsItem

interface TestResultRepository {
    suspend fun createStat(stat: StatsItem)
}