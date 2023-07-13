package com.affan.suitmedia.di

import android.content.Context
import com.affan.suitmedia.network.ApiConfig
import com.affan.suitmedia.paging.UserRepository


object Injection {
    fun provideRepository(context: Context): UserRepository {

        val apiService = ApiConfig.getApiService()
        return UserRepository(apiService)
    }
}