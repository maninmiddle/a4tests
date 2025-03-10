package com.maninmiddle.features.test_create.domain.usecase

import com.maninmiddle.features.test_create.domain.model.TestItem
import com.maninmiddle.features.test_create.domain.repository.CreateTestRepository

class CreateTestUseCase(
    private val repository: CreateTestRepository
) {
    suspend operator fun invoke(testItem: TestItem) = repository.createTest(testItem)
}