package com.rsf.challengeapp.service.model

import com.google.gson.annotations.SerializedName

open class CommonProduct {
    @SerializedName("name")
    open var name: String = ""

    @SerializedName("imageURL")
    open var imageURL: String = ""

    @SerializedName("description")
    open var description: String = ""
}
