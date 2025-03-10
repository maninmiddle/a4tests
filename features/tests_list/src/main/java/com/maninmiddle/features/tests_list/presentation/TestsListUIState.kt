package com.maninmiddle.features.tests_list.presentation

import com.maninmiddle.features.tests_list.domain.model.TaskModel
import com.maninmiddle.features.tests_list.domain.model.TestItem

data class TestsListUIState(
    val tests: List<TestItem>? = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)