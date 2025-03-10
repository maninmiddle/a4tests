package com.maninmiddle.features.test_create.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maninmiddle.core.common.network.ApiState
import com.maninmiddle.features.test_create.domain.model.TaskModel
import com.maninmiddle.features.test_create.domain.model.TestItem
import com.maninmiddle.features.test_create.domain.usecase.CreateTasksUseCase
import com.maninmiddle.features.test_create.domain.usecase.CreateTestUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TestCreateViewModel(
    private val createTestUseCase: CreateTestUseCase,
    private val createTasksUseCase: CreateTasksUseCase
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

    fun createTasks(tasks: List<TaskModel>) {
        viewModelScope.launch {
            createTasksUseCase.invoke(tasks)
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