package com.abilashcse.birthdayapp

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BirthdayApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.d("Test", "SampleApp main")
    }
}
