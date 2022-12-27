package com.example.binlistn.di

import com.example.binlistn.data.BankApi
import com.example.binlistn.network.RetrofitProvider
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

val networkModule = module {
    single { RetrofitProvider().invoke() }
    single { provideApi(get()) }
}
private fun provideApi(retrofit: Retrofit) = retrofit.create<BankApi>()