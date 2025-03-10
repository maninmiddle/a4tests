package com.maninmiddle.features.test_result.data.repository

import com.maninmiddle.core.common.network.ApiService
import com.maninmiddle.features.test_result.data.mapper.toStatsItemDto
import com.maninmiddle.features.test_result.domain.model.StatsItem
import com.maninmiddle.features.test_result.domain.repository.TestResultRepository

class TestResultRepositoryImpl(
    private val apiService: ApiService
) : TestResultRepository {
    override suspend fun createStat(stat: StatsItem) {
        apiService.createStat(stat.toStatsItemDto())
    }
}