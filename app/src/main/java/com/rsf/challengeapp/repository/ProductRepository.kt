package com.rsf.challengeapp.repository

import com.rsf.challengeapp.datasource.IProductDataSource
import com.rsf.challengeapp.model.Product
import com.rsf.challengeapp.util.BaseException
import com.rsf.challengeapp.util.Result

class ProductRepository(private val dataSource: IProductDataSource): IProductRepository {

    override suspend fun getProductList(): Result<List<Product>> {
        return try {
            Result.Success(dataSource.fetchProductList())
        } catch (exc: BaseException) {
            Result.Failure(exc)
        }
    }
}