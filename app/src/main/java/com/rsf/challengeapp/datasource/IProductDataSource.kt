package com.rsf.challengeapp.datasource

import com.rsf.challengeapp.model.Product

interface IProductDataSource {
    suspend fun fetchProductList(): List<Product>
}