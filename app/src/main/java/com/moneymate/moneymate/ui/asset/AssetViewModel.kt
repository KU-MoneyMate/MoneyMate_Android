package com.moneymate.moneymate.ui.asset

import androidx.lifecycle.ViewModel
import com.moneymate.moneymate.data.repository.AssetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AssetViewModel @Inject constructor(
    private val assetRepository: AssetRepository
): ViewModel() {

}