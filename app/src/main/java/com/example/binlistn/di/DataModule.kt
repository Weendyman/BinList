package com.example.binlistn.di

import com.example.binlistn.data.BinListRepository
import com.example.binlistn.data.BinListRepositoryImpl
import org.koin.dsl.module

val dataModule = module {
    factory<BinListRepository> { BinListRepositoryImpl(get(), get()) }
}