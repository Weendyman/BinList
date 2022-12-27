package com.example.binlistn.data

import com.example.binlistn.data.database.BinListDao
import com.example.binlistn.ui.search.SearchResultContent
import com.example.binlistn.ui.search.saved.SavedBankContent

class BinListRepositoryImpl(
    private val bankApi: BankApi,
    private val bankDao: BinListDao
) : BinListRepository {

    override suspend fun getBankInfo(bin: String): Result<SearchResultContent> {
        val response = bankApi.getBankInfo(buildRequestUrl(bin))
        val responseBody = response.body()
        return if (response.isSuccessful && responseBody != null) {
            Result.success(responseBody.toSearchResultContent())
        } else {
            Result.failure(Throwable("Не удалось загрузить все данные"))
        }
    }

    override suspend fun insert(savedBankContent: SavedBankContent) {
        bankDao.insert(savedBankContent)
    }

    override suspend fun getAllSavedHistory(): List<SavedBankContent> {
        return bankDao.getAllBinHistory()
    }

    private fun buildRequestUrl(bin: String) =
        "https://lookup.binlist.net/$bin"

    private fun BankResponse.toSearchResultContent() =
        SearchResultContent(
            scheme = scheme,
            brand = brand,
            cardNumber = number.length.toString(),
            type = type,
            prepaid = prepaid.toString(),
            country = country.name,
            bank = bank.name,
            url = bank.url
        )
}
