package com.vk.fitnesstracker

import android.app.Application
import com.yandex.mapkit.MapKitFactory

class ApplicationInitialize : Application() {
    override fun onCreate() {
        super.onCreate()
        // Yandex mapkit api key
        MapKitFactory.setApiKey(resources.getString(R.string.yandex_key))
        initializer=this
    }

    companion object {
        @get:Synchronized
        lateinit var initializer:ApplicationInitialize
        private set
    }
}