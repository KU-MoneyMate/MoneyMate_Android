package com.moneymate.moneymate.ui.manage

import androidx.lifecycle.ViewModel
import com.moneymate.moneymate.data.service.ManageService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ManageViewModel @Inject constructor(
    private val manageService: ManageService
): ViewModel() {

}