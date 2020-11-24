package com.rsf.challengeapp.repository

import com.rsf.challengeapp.model.Product
import com.rsf.challengeapp.util.Result

interface IProductRepository {
    suspend fun getProductList(): Result<List<Product>>
}