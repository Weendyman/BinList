package com.example.binlistn.data

import com.example.binlistn.ui.search.SearchResultContent
import com.example.binlistn.ui.search.saved.SavedBankContent
import retrofit2.Response

interface BinListRepository {

    suspend fun getBankInfo(bin: String): Result<SearchResultContent>

    suspend fun insert(savedBankContent: SavedBankContent)

    suspend fun getAllSavedHistory(): List<SavedBankContent>
}