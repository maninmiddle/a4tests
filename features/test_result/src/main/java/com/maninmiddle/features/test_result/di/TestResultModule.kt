package com.maninmiddle.features.test_result.di

import com.maninmiddle.features.test_result.data.repository.TestResultRepositoryImpl
import com.maninmiddle.features.test_result.domain.repository.TestResultRepository
import com.maninmiddle.features.test_result.domain.usecase.CreateStatUseCase
import com.maninmiddle.features.test_result.presentation.TestResultViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val testResultModule = module {
    single<TestResultRepository> { TestResultRepositoryImpl(get()) }
    single<CreateStatUseCase> { CreateStatUseCase(get()) }
    viewModel { TestResultViewModel(get()) }
}