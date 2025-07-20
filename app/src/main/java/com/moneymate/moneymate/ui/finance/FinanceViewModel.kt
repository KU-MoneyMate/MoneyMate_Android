package com.moneymate.moneymate.ui.finance

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moneymate.moneymate.data.dto.finance.NewsInfo
import com.moneymate.moneymate.data.repository.FinanceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FinanceViewModel @Inject constructor(
    private val financeRepository: FinanceRepository
): ViewModel() {

    private val _newsList = mutableStateOf<List<NewsInfo>>(emptyList())
    val newsList: State<List<NewsInfo>> = _newsList

    private val _categoryNewsList = mutableStateOf<List<NewsInfo>>(emptyList())
    val categoryNewsList: State<List<NewsInfo>> = _categoryNewsList

    init {
        getNewsList()
    }

    fun getNewsList(){
        viewModelScope.launch {
            runCatching { financeRepository.getNewsList() }
                .onSuccess {
                    _newsList.value = it
                    Log.d("FinanceViewModel", "조회 성공")
                }
                .onFailure { response ->
                    response.message?.let { Log.d("FinanceViewModel", response.message.toString()) }
                }
        }
    }

    fun getCategoryNews(publisher : String, category : String) {
        viewModelScope.launch {
            runCatching { financeRepository.getCategoryNews(publisher, category) }
                .onSuccess {
                    _categoryNewsList.value = it
                    Log.d("FinanceViewModel", "조회 성공")
                }
                .onFailure { response ->
                    response.message?.let { Log.d("FinanceViewModel", response.message.toString()) }
                }
        }
    }


}