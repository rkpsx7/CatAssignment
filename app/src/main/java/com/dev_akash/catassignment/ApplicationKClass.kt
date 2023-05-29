package com.dev_akash.catassignment

import android.app.Application
import com.dev_akash.catassignment.utils.network.AuthTokenManager
import com.dev_akash.catassignment.utils.SharedPrefs
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class ApplicationKClass : Application() {

    @Inject
    lateinit var tokenManager: AuthTokenManager

    override fun onCreate() {
        super.onCreate()
//        INSTANCE = this
        SharedPrefs.loadAppPrefs(this)
        mockSavingTokenToPrefs()
    }


    private fun mockSavingTokenToPrefs() {
        tokenManager.saveToken("live_iWgF011c57D99c5MOyJOXwvkyYorfT0lk860SxrZwFJRPCWCWYQZMwF87SV7NlyI")
    }

}