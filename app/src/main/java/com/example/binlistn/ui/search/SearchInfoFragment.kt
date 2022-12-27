package com.example.binlistn.ui.search

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.binlistn.R
import com.example.binlistn.databinding.FragmentSearchInfoBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchInfoFragment : Fragment(R.layout.fragment_search_info) {

    private val searchInfoBinding: FragmentSearchInfoBinding by viewBinding()
    private val viewModel: SearchInfoViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchInfoBinding.searchButton.setOnClickListener {
            viewModel.searchBankInfo(
                searchInfoBinding.binSearchEt.text.toString()
            )
        }
        searchInfoBinding.showHistoryButton.setOnClickListener {
            viewModel.getSavedBinList()
            showToast(view, "Данные выведены в логи!)")
        }
        viewModel.uiState.flowWithLifecycle(lifecycle)
            .onEach {
                when (it) {
                    is SearchInfoUIState.Content -> bindBankInfo(it.searchResultContent)
                    is SearchInfoUIState.Error -> showToast(
                        view,
                        "Ошибка! Не удалось загрузить данные. Проверьте правильность bin'а и повторите попытку"
                    )
                    is SearchInfoUIState.Start -> {}
                }
            }.launchIn(lifecycleScope)
    }

    private fun bindBankInfo(searchResultContent: SearchResultContent) = with(searchInfoBinding) {
        schemeTv.text = searchResultContent.scheme
        brandTv.text = searchResultContent.brand
        cardNumberTv.text = searchResultContent.cardNumber
        typeTv.text = searchResultContent.type
        prepaidTv.text = searchResultContent.prepaid
        countryTv.text = searchResultContent.country
        bankTv.text = searchResultContent.bank
        urlTv.text = searchResultContent.url
        urlTv.setOnClickListener {
            openBrowser(searchResultContent.url)
        }
    }

    private fun openBrowser(url: String) {
        var completedUrl = url
        if (!url.startsWith("http://") && !url.startsWith("https://"))
            completedUrl = "http://$url"
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(completedUrl))
        startActivity(browserIntent)
    }

    private fun showToast(view: View, text: String) {
        val snack = Snackbar.make(view, text, Snackbar.LENGTH_LONG)
        snack.show()
    }
}