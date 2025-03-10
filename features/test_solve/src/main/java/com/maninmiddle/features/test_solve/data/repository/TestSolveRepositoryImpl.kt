package com.maninmiddle.features.test_solve.data.repository

import com.maninmiddle.core.common.network.ApiService
import com.maninmiddle.core.common.network.ApiState
import com.maninmiddle.features.test_solve.data.mapper.toTasksModel
import com.maninmiddle.features.test_solve.domain.model.TaskModel
import com.maninmiddle.features.test_solve.domain.repository.TestSolveRepository

class TestSolveRepositoryImpl(
    private val apiService: ApiService
) : TestSolveRepository {
    override suspend fun getTasksForTest(testId: Int): ApiState<List<TaskModel>> {
        return try {
            ApiState.Success(data = apiService.getTasksByTestId(testId).toTasksModel())
        } catch (e: Exception) {
            e.printStackTrace()
            ApiState.Error(message = e.message ?: "An unknown error occurred")
        }
    }
}