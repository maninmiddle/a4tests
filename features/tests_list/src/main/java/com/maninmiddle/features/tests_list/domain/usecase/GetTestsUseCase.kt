package com.maninmiddle.features.tests_list.domain.usecase

import com.maninmiddle.features.tests_list.domain.repository.TestsListRepository

class GetTestsUseCase(
    private val repository: TestsListRepository
) {
    suspend operator fun invoke() = repository.getTests()
}