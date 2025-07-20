package com.moneymate.moneymate.ui.finance

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moneymate.moneymate.data.dto.finance.response.NewsInfo
import com.moneymate.moneymate.data.repository.FinanceRepository
import com.moneymate.moneymate.data.service.FinanceService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FinanceViewModel @Inject constructor(
    private val financeRepository: FinanceRepository
): ViewModel() {

    private val _newsList = mutableStateOf<List<NewsInfo>>(emptyList())
    val newsList: State<List<NewsInfo>> = _newsList

    init {
        getNewsList()
    }

    fun getNewsList(){
        viewModelScope.launch {
            runCatching { financeRepository.getNewsList() }
                .onSuccess {
                    _newsList.value = it
                    Log.d("AssetViewModel", "조회 성공")
                }
                .onFailure { response ->
                    response.message?.let { Log.d("AssetViewModel", response.message.toString()) }
                }
        }
    }


}