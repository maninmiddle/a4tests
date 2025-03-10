package com.maninmiddle.core.common.di

import com.maninmiddle.core.common.network.ApiService
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }
    single {
        get<Retrofit>().create(ApiService::class.java)
    }
}