package com.maninmiddle.features.test_solve.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maninmiddle.core.common.network.ApiState
import com.maninmiddle.features.test_solve.domain.usecases.GetTasksForTestUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TestSolveViewModel(
    private val getTasksForTestUseCase: GetTasksForTestUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(TestSolveUIState())
    val state: StateFlow<TestSolveUIState>
        get() = _state


    fun getTasks(testId: Int) {
        viewModelScope.launch {
            _state.value = state.value.copy(
                isLoading = true,
                tasks = null,
                error = null
            )
            when (val result = getTasksForTestUseCase(testId)) {
                is ApiState.Success -> {
                    _state.value = state.value.copy(
                        isLoading = false,
                        tasks = result.data,
                        error = null
                    )
                }

                is ApiState.Error -> {
                    _state.value = state.value.copy(
                        isLoading = false,
                        tasks = null,
                        error = result.message
                    )
                }

                else -> Log.e("Api call error", result.message.toString())
            }
        }
    }
}