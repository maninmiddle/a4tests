package com.maninmiddle.features.test_result.domain.usecase

import com.maninmiddle.features.test_result.domain.model.StatsItem
import com.maninmiddle.features.test_result.domain.repository.TestResultRepository

class CreateStatUseCase(
    private val repository: TestResultRepository
) {
    suspend operator fun invoke(stat: StatsItem) = repository.createStat(stat)
}