package com.affan.suitmedia.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.affan.suitmedia.di.Injection
import com.affan.suitmedia.network.DataItem
import com.affan.suitmedia.paging.UserRepository

class UserViewModel (repository: UserRepository) : ViewModel() {

    val user: LiveData<PagingData<DataItem>> =
        repository.getUsers().cachedIn(viewModelScope)
}

    class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserViewModel(Injection.provideRepository(context)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}