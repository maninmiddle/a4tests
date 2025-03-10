package com.maninmiddle.features.test_create.domain.usecase

import com.maninmiddle.features.test_create.domain.model.TaskModel
import com.maninmiddle.features.test_create.domain.repository.CreateTestRepository

class CreateTasksUseCase(
    private val repository: CreateTestRepository
) {
    suspend operator fun invoke(tasks: List<TaskModel>) = repository.createTasks(tasks)
}