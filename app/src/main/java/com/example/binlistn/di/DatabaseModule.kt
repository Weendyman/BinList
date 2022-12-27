package com.example.binlistn.di

import android.content.Context
import androidx.room.Room
import com.example.binlistn.data.database.AppDatabase
import com.example.binlistn.data.database.BinListDao
import org.koin.dsl.module

val databaseModule = module {
    single { provideDatabase(get()) }
//    factory { get<AppDatabase>().binListDao() }
}

private fun provideDatabase(context: Context): BinListDao {
    return Room.databaseBuilder(context, AppDatabase::class.java, "bin-list-db.db")
        .fallbackToDestructiveMigration()
        .build()
        .binListDao()
}