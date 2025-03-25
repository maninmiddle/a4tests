package com.maninmiddle.core.common.di

import com.maninmiddle.core.common.network.ApiService
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://947b-95-211-172-88.ngrok-free.app")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }
    single {
        get<Retrofit>().create(ApiService::class.java)
    }
}