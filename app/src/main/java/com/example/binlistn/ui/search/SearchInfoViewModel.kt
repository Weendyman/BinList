package com.example.binlistn.ui.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.binlistn.data.BinListRepository
import com.example.binlistn.ui.search.saved.SavedBankContent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchInfoViewModel(
    private val binListRepository: BinListRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<SearchInfoUIState>(SearchInfoUIState.Start)
    val uiState = _uiState.asStateFlow()

    fun searchBankInfo(bin: String) {
        viewModelScope.launch {
            val result = binListRepository.getBankInfo(bin)
            val bankContent = result.getOrNull()
            if (result.isSuccess && bankContent != null) {
                saveBinInfoToHistory(bankContent)
                _uiState.tryEmit(SearchInfoUIState.Content(bankContent))
            } else {
                _uiState.tryEmit(SearchInfoUIState.Error)
            }
        }
    }

    private suspend fun saveBinInfoToHistory(searchBankContent: SearchResultContent) {
        val savedBankContent = searchBankContent.toSavedBankContent()
        binListRepository.insert(savedBankContent)
    }

    fun getSavedBinList() {
        viewModelScope.launch {
            Log.d("TAG", binListRepository.getAllSavedHistory().toString())
        }
    }

    private fun SearchResultContent.toSavedBankContent() =
        SavedBankContent(
            scheme = scheme,
            bank = bank,
            country = country,
            url = url
        )
}

sealed interface SearchInfoUIState {
    data class Content(val searchResultContent: SearchResultContent) : SearchInfoUIState
    object Error : SearchInfoUIState
    object Start : SearchInfoUIState
}