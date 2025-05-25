package com.moneymate.moneymate

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MoneyMateApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}