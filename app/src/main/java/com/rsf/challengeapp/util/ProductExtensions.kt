package com.rsf.challengeapp.util

import com.rsf.challengeapp.model.Normal
import com.rsf.challengeapp.model.Product
import com.rsf.challengeapp.model.Special
import com.rsf.challengeapp.model.SpotLight
import com.rsf.challengeapp.service.model.CashProduct
import com.rsf.challengeapp.service.model.CommonProduct
import com.rsf.challengeapp.service.model.FeaturedProduct

fun CommonProduct.toModel(): Product {
    val type = when(this) {
        is CashProduct -> Special
        is FeaturedProduct -> SpotLight
        else -> Normal
    }
    return Product(
        name, description, imageURL, type
    )
}