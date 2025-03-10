package com.maninmiddle.features.test_solve.di

import com.maninmiddle.features.test_solve.data.repository.TestSolveRepositoryImpl
import com.maninmiddle.features.test_solve.domain.repository.TestSolveRepository
import com.maninmiddle.features.test_solve.domain.usecases.GetTasksForTestUseCase
import com.maninmiddle.features.test_solve.presentation.TestSolveViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val testSolveModule = module {
    single<TestSolveRepository> { TestSolveRepositoryImpl(get()) }
    single<GetTasksForTestUseCase> { GetTasksForTestUseCase(get()) }
    viewModel { TestSolveViewModel(get()) }
}