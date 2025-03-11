package com.maninmiddle.features.test_solve.presentation

import android.os.CountDownTimer
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

    private val _timerValue = MutableStateFlow<Long>(0)
    val timerValue: StateFlow<Long>
        get() = _timerValue


    private var _canContinue = MutableStateFlow(true)
    val canContinue: StateFlow<Boolean>
        get() = _canContinue

    private var countDownTimer: CountDownTimer? = null


    fun startTimer(mills: Long) {
        _canContinue.value = true
        countDownTimer = object : CountDownTimer(mills, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                _timerValue.value = millisUntilFinished
            }

            override fun onFinish() {
                _canContinue.value = false
            }


        }.start()

    }

    fun changeStateCanContinue() {
        _canContinue.value = !_canContinue.value
    }


    fun cancelTimer() {
        _canContinue.value = false
        countDownTimer?.cancel()
    }


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