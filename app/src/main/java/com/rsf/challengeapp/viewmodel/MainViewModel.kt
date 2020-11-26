package com.rsf.challengeapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.rsf.challengeapp.repository.IProductRepository
import kotlinx.coroutines.delay

class MainViewModel(private val repository: IProductRepository): ViewModel() {

    fun getProductList() = liveData {
        delay(500)
        emit(repository.getProductList())
    }
}