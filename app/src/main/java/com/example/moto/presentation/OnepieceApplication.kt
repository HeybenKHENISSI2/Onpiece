package com.example.moto.presentation

import android.app.Application
import android.content.Context


class OnepieceApplication : Application() {
    companion object {
        var context: Context? = null
    }
    override fun onCreate() {
        super.onCreate()
        context = this
    }
}