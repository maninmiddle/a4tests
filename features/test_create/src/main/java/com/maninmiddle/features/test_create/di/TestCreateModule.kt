package com.maninmiddle.features.test_create.di

import com.maninmiddle.features.test_create.data.repository.CreateTestRepositoryImpl
import com.maninmiddle.features.test_create.domain.repository.CreateTestRepository
import com.maninmiddle.features.test_create.domain.usecase.CreateTasksUseCase
import com.maninmiddle.features.test_create.domain.usecase.CreateTestUseCase
import com.maninmiddle.features.test_create.domain.usecase.GenerateTasksUseCase
import com.maninmiddle.features.test_create.presentation.TestCreateViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val testCreateModule = module {
    single<CreateTestRepository> { CreateTestRepositoryImpl(get()) }
    single<CreateTestUseCase> { CreateTestUseCase(get()) }
    single<CreateTasksUseCase> { CreateTasksUseCase(get()) }
    single<GenerateTasksUseCase> { GenerateTasksUseCase(get()) }
    viewModel { TestCreateViewModel(get(), get(), get()) }
}