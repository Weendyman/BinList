package com.example.binlistn.di

import com.example.binlistn.data.BinListRepository
import com.example.binlistn.ui.SearchInfoViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule=module{viewModel { SearchInfoViewModel(get()) }}