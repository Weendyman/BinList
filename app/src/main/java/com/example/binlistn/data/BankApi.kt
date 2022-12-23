package com.example.binlistn.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BankApi {

    @GET("45717360")
    suspend fun getBankInfo(
        binNumber: String
    ): Response<BankResponse>
}