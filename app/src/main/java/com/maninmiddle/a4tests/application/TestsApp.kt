package com.maninmiddle.a4tests.application

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.maninmiddle.core.common.di.networkModule
import com.maninmiddle.features.test_create.di.testCreateModule
import com.maninmiddle.features.test_result.di.testResultModule
import com.maninmiddle.features.test_solve.di.testSolveModule
import com.maninmiddle.features.tests_list.di.testsListModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class TestsApp : Application() {
    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@TestsApp)
            modules(networkModule, testsListModule, testSolveModule, testCreateModule, testResultModule)
        }
    }
}