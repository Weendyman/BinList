package com.example.binlistn.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Url

interface BankApi {

    @GET
    suspend fun getBankInfo(
        @Url url: String
    ): Response<BankResponse>
}