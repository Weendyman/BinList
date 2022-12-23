package com.example.binlistn.data

import retrofit2.Response

interface BinListRepository {

  suspend  fun getBankInfo(bin:String):Response<BankResponse>

}