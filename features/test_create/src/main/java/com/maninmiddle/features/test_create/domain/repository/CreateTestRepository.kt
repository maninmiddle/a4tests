package com.maninmiddle.features.test_create.domain.repository

import com.maninmiddle.core.common.network.ApiState
import com.maninmiddle.features.test_create.domain.model.TaskModel
import com.maninmiddle.features.test_create.domain.model.TestItem

interface CreateTestRepository {
    suspend fun createTest(testItem: TestItem): ApiState<TestItem>
    suspend fun createTasks(tasks: List<TaskModel>)
    suspend fun generateTasks(text: String): ApiState<List<TaskModel>>
}