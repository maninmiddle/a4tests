package com.maninmiddle.features.test_result.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maninmiddle.features.test_result.domain.model.StatsItem
import com.maninmiddle.features.test_result.domain.usecase.CreateStatUseCase
import kotlinx.coroutines.launch

class TestResultViewModel(
    private val createStatUseCase: CreateStatUseCase
) : ViewModel() {

    fun createStat(stat: StatsItem) {
        viewModelScope.launch {
            createStatUseCase.invoke(stat)
        }
    }
}