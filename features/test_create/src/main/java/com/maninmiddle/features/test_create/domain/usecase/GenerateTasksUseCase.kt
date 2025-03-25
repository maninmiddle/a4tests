package com.maninmiddle.features.test_create.domain.usecase

import com.maninmiddle.features.test_create.domain.repository.CreateTestRepository

class GenerateTasksUseCase(
    private val repository: CreateTestRepository
) {
    suspend operator fun invoke(text: String) = repository.generateTasks(text)
}