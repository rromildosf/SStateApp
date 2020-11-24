package com.rsf.challengeapp.service.model

import com.google.gson.annotations.SerializedName

open class FeaturedProduct: CommonProduct() {
    @SerializedName("bannerURL")
    override var imageURL: String = ""
}