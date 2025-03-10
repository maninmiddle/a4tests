package com.maninmiddle.features.test_solve.domain.repository

import com.maninmiddle.core.common.network.ApiState
import com.maninmiddle.features.test_solve.domain.model.TaskModel

interface TestSolveRepository {
    suspend fun getTasksForTest(testId: Int): ApiState<List<TaskModel>>
}