package com.rsf.challengeapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.rsf.challengeapp.repository.IProductRepository

class MainViewModel(private val repository: IProductRepository): ViewModel() {

    fun getProductList() = liveData {
        emit(repository.getProductList())
    }
}