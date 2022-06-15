package com.bbi.bizbulls

import android.app.Application
import android.content.Context

/**
 * Created by Daniel.
 */
class MainApplication : Application() {

    private var context: Context? = null

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    fun getAppContext(): Context? {
        return context
    }
}