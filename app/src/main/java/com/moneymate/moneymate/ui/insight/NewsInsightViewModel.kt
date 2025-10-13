package com.moneymate.moneymate.ui.insight

import androidx.lifecycle.ViewModel
import com.moneymate.moneymate.data.repository.FinanceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsInsightViewModel @Inject constructor(
    private val repository: FinanceRepository
): ViewModel() {

}