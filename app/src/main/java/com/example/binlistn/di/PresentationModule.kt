package com.example.binlistn.di

import com.example.binlistn.ui.search.SearchInfoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { SearchInfoViewModel(get()) }
}
