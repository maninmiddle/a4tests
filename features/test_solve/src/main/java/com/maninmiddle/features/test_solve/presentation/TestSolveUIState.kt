package com.maninmiddle.features.test_solve.presentation

import com.maninmiddle.features.test_solve.domain.model.TaskModel

data class TestSolveUIState(
    val tasks: List<TaskModel>? = emptyList(),
    val error: String? = null,
    val isLoading: Boolean = false
)