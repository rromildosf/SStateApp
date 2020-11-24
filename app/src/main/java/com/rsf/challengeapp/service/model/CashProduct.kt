package com.rsf.challengeapp.service.model

import com.google.gson.annotations.SerializedName

class CashProduct: FeaturedProduct() {
    @SerializedName("title")
    override var name: String = ""
}