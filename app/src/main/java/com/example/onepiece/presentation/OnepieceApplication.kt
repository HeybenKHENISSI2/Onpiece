package com.example.onepiece.presentation

import android.app.Application
import android.content.Context


class OnepiecApplication : Application() {
    companion object {
        var context: Context? = null
    }
    override fun onCreate() {
        super.onCreate()
        context = this
    }
}