package ru.home.unipark

import android.app.Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object Factory {
        private lateinit var instance: App

        fun getInstance(): App {
            return instance
        }
    }
}