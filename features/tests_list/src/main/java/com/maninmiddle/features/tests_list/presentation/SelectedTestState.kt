package com.maninmiddle.features.tests_list.presentation

import com.maninmiddle.features.tests_list.domain.model.TaskModel
import com.maninmiddle.features.tests_list.domain.model.TestItem

data class SelectedTestState(
    val test: TestItem? = null,
    val tasks: List<TaskModel>? = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)