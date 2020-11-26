package com.rsf.challengeapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.rsf.challengeapp.model.Product
import com.rsf.challengeapp.repository.IProductRepository
import com.rsf.challengeapp.util.Result
import kotlinx.coroutines.delay

class MainViewModel(private val repository: IProductRepository): ViewModel() {
    var selectedProduct: Product? = null
    var products: List<Product>? = null

    fun getProductList() = liveData {
        if (products != null) emit(Result.Success(products!!))
        else {
            val result = repository.getProductList()
            products = result.success

            emit(result)
        }
    }
}