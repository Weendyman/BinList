package com.example.binlistn.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitProvider : () -> Retrofit {
    override fun invoke(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://lookup.binlist.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(createInterceptor())
            .build()
    }

    private fun createInterceptor(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }
}
