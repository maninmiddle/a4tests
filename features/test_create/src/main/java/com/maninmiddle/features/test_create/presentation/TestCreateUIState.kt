package com.maninmiddle.features.test_create.presentation

import com.maninmiddle.features.test_create.domain.model.TestItem

data class TestCreateUIState(
    val test: TestItem?,
    val isLoading: Boolean = false,
    val error: String? = null,
)