package com.rsf.challengeapp.datasource

import com.rsf.challengeapp.model.Product
import com.rsf.challengeapp.service.ItemService
import com.rsf.challengeapp.util.*
import retrofit2.HttpException
import java.lang.Exception
import java.net.ConnectException
import java.net.UnknownHostException

class ProductDataSource(private val service: ItemService): IProductDataSource {

    override suspend fun fetchProductList(): List<Product> {
        try {
            val data = service.fetchList()

            return mutableListOf<Product>().apply {
                data.cash?.let { add(it.toModel()) }
                data.featured?.forEach { add(it.toModel()) }
                data.products?.forEach { add(it.toModel()) }
            }
        } catch (exc: Exception) {
            throw convertException(exc)
        }
    }

    private fun convertException(exception: Exception): BaseException {
        return when (exception) {
            is ConnectException,
            is UnknownHostException -> UnreachableNetworkException
            is HttpException -> NetworkCallException(exception.code().toString(), exception.message())
            else -> UnknownException(exception.message ?: "")
        }
    }

}