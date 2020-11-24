package com.rsf.challengeapp.service.model

import com.google.gson.annotations.SerializedName

class ItemResponse {
    @SerializedName("spotlight") val featured: List<FeaturedProduct>? = null
    @SerializedName("products") val products: List<CommonProduct>? = null
    @SerializedName("cash") val cash: CashProduct? = null
}