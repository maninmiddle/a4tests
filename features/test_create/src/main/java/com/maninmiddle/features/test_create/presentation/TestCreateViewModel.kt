package com.maninmiddle.features.test_create.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maninmiddle.core.common.network.ApiState
import com.maninmiddle.features.test_create.domain.model.TaskModel
import com.maninmiddle.features.test_create.domain.model.TestItem
import com.maninmiddle.features.test_create.domain.usecase.CreateTasksUseCase
import com.maninmiddle.features.test_create.domain.usecase.CreateTestUseCase
import com.maninmiddle.features.test_create.domain.usecase.GenerateTasksUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TestCreateViewModel(
    private val createTestUseCase: CreateTestUseCase,
    private val createTasksUseCase: CreateTasksUseCase,
    private val generateTasksUseCase: GenerateTasksUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(
        TestCreateUIState(
            TestItem(
                id = 0,
                name = "",
                subject = "",
                completeTime = 0,
                password = ""
            )
        )
    )

    val state: StateFlow<TestCreateUIState> = _state

    private val _generateState = MutableStateFlow(TaskGenerateUIState(emptyList()))
    val generateState: StateFlow<TaskGenerateUIState> = _generateState

    fun createTasks(tasks: List<TaskModel>) {
        viewModelScope.launch {
            createTasksUseCase.invoke(tasks)
        }
    }

    fun generateTasks(text: String) {
        viewModelScope.launch {
            _generateState.value = generateState.value.copy(
                tasks = null,
                isLoading = true,
                error = null
            )

            when (val result = generateTasksUseCase.invoke(text)) {
                is ApiState.Success -> {
                    _generateState.value = generateState.value.copy(
                        isLoading = false,
                        error = null,
                        tasks = result.data
                    )
                }

                is ApiState.Error -> {
                    _generateState.value = generateState.value.copy(
                        isLoading = false,
                        error = result.message,
                        tasks = null
                    )
                }

                else -> Log.e("API Call Error!", result.message.toString())
            }
        }
    }

    fun createTest(test: TestItem) {
        viewModelScope.launch {
            _state.value = state.value.copy(
                test = null,
                isLoading = true,
                error = null
            )

            when (val result = createTestUseCase.invoke(test)) {
                is ApiState.Success -> {
                    _state.value = state.value.copy(
                        isLoading = false,
                        error = null,
                        test = result.data
                    )
                }

                is ApiState.Error -> {
                    _state.value = state.value.copy(
                        isLoading = false,
                        error = result.message,
                        test = null
                    )
                }

                else -> Log.e("API Call Error!", result.message.toString())
            }
        }
    }
}