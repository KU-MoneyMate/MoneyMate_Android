package com.moneymate.moneymate.ui.finance

import androidx.lifecycle.ViewModel
import com.moneymate.moneymate.data.service.FinanceService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FinanceViewModel @Inject constructor(
    private val financeService: FinanceService
): ViewModel() {

}