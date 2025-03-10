package com.maninmiddle.features.test_solve.domain.usecases

import com.maninmiddle.features.test_solve.domain.repository.TestSolveRepository

class GetTasksForTestUseCase(
    private val repository: TestSolveRepository
) {
    suspend operator fun invoke(testId: Int) = repository.getTasksForTest(testId)
}