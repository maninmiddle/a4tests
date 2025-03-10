package com.maninmiddle.features.tests_list.domain.repository

import com.maninmiddle.core.common.network.ApiState
import com.maninmiddle.features.tests_list.domain.model.TaskModel
import com.maninmiddle.features.tests_list.domain.model.TestItem

interface TestsListRepository {
    suspend fun getTests(): ApiState<List<TestItem>>
}