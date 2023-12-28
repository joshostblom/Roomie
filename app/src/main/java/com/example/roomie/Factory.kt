package com.example.roomie

import android.content.Context

class Factory {
    companion object {
        private var sharedFactory: Factory? = null

        @JvmStatic
        fun shared(): Factory {
            if (sharedFactory == null) {
                sharedFactory = Factory()
            }
            return sharedFactory!!
        }
    }

    // Context

    private lateinit var context: Context

    fun getApplicationContext(): Context {
        return context
    }

    fun setApplicationContext(appContext: Context) {
        context = appContext
    }
}