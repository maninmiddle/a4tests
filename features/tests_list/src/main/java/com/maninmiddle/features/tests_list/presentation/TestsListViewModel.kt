package com.maninmiddle.features.tests_list.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maninmiddle.core.common.network.ApiState
import com.maninmiddle.features.tests_list.domain.model.TestItem
import com.maninmiddle.features.tests_list.domain.repository.TestsListRepository
import com.maninmiddle.features.tests_list.domain.usecase.GetTestsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TestsListViewModel(
    private val getTestsUseCase: GetTestsUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(TestsListUIState())

    val state: StateFlow<TestsListUIState>
        get() = _state

    init {
        loadTests()
    }

    private fun loadTests() {
        viewModelScope.launch {
            _state.value = state.value.copy(
                isLoading = true,
                error = null,
                tests = null
            )
            when (val result = getTestsUseCase()) {
                is ApiState.Success -> {
                    _state.value = state.value.copy(
                        isLoading = false,
                        error = null,
                        tests = result.data
                    )
                }

                is ApiState.Error -> {
                    _state.value = state.value.copy(
                        isLoading = false,
                        error = result.message,
                        tests = null
                    )
                }

                else -> Log.e("API Call Error!", result.message.toString())
            }
        }
    }
}