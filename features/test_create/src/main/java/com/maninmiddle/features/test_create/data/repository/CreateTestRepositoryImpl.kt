package com.maninmiddle.features.test_create.data.repository

import com.maninmiddle.core.common.network.ApiService
import com.maninmiddle.core.common.network.ApiState
import com.maninmiddle.features.test_create.data.mapper.toListTaskItemDto
import com.maninmiddle.features.test_create.data.mapper.toTestItem
import com.maninmiddle.features.test_create.data.mapper.toTestItemDto
import com.maninmiddle.features.test_create.domain.model.TaskModel
import com.maninmiddle.features.test_create.domain.model.TestItem
import com.maninmiddle.features.test_create.domain.repository.CreateTestRepository

class CreateTestRepositoryImpl(
    private val apiService: ApiService
) : CreateTestRepository {
    override suspend fun createTest(testItem: TestItem): ApiState<TestItem> {
        return try {
            ApiState.Success(data = apiService.createTest(testItem.toTestItemDto()).toTestItem())
        } catch (e: Exception) {
            ApiState.Error(message = e.message ?: "An unknown message occurred")
        }
    }

    override suspend fun createTasks(tasks: List<TaskModel>) {
        apiService.createTasks(tasks.toListTaskItemDto())
    }
}