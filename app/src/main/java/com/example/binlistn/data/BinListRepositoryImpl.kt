package com.example.binlistn.data

import retrofit2.Response


class BinListRepositoryImpl (val bankApi: BankApi) :BinListRepository {
    override suspend fun getBankInfo(bin: String): Response<BankResponse> {
        return bankApi.getBankInfo(bin)
    }
}