package com.maninmiddle.features.tests_list.di

import com.maninmiddle.features.tests_list.data.repository.TestsListRepositoryImpl
import com.maninmiddle.features.tests_list.domain.repository.TestsListRepository
import com.maninmiddle.features.tests_list.domain.usecase.GetTestsUseCase
import com.maninmiddle.features.tests_list.presentation.TestsListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val testsListModule = module {
    single<TestsListRepository> { TestsListRepositoryImpl(get()) }
    single<GetTestsUseCase> { GetTestsUseCase(get()) }
    viewModel { TestsListViewModel(get()) }
}