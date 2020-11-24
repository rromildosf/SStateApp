package com.rsf.challengeapp.service

import com.rsf.challengeapp.service.model.ItemResponse
import retrofit2.http.GET

interface ItemService {
    @GET("products")
    suspend fun fetchList(): ItemResponse
}