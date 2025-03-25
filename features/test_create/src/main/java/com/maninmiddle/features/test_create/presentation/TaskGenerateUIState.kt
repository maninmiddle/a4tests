package com.maninmiddle.features.test_create.presentation

import com.maninmiddle.features.test_create.domain.model.TaskModel

data class TaskGenerateUIState(
    val tasks: List<TaskModel>?,
    val isLoading: Boolean = false,
    val error: String? = null,
)