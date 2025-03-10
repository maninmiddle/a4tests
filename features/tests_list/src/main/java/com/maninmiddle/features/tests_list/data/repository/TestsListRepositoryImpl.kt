package com.maninmiddle.features.tests_list.data.repository

import com.maninmiddle.core.common.network.ApiService
import com.maninmiddle.core.common.network.ApiState
import com.maninmiddle.features.tests_list.data.mapper.toListTestItem
import com.maninmiddle.features.tests_list.data.mapper.toTestItem
import com.maninmiddle.features.tests_list.data.mapper.toTestItemDto
import com.maninmiddle.features.tests_list.domain.model.TaskModel
import com.maninmiddle.features.tests_list.domain.model.TestItem
import com.maninmiddle.features.tests_list.domain.repository.TestsListRepository

class TestsListRepositoryImpl(
    private val apiService: ApiService
) : TestsListRepository {
    override suspend fun getTests(): ApiState<List<TestItem>> {
        return try {
            ApiState.Success(data = apiService.getTests().toListTestItem())
        } catch (e: Exception) {
            e.printStackTrace()
            ApiState.Error(message = e.message ?: "An unknown error occurred")
        }
    }


}