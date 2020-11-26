package com.rsf.challengeapp.util

import com.rsf.challengeapp.R
import com.rsf.challengeapp.model.Normal
import com.rsf.challengeapp.model.Product
import com.rsf.challengeapp.model.Special
import com.rsf.challengeapp.model.SpotLight

fun createSkeletonProductList(): List<Product> {
    val products = mutableListOf<Product>()
    repeat(3) {
        products.add(Product("", "", "drawable://${R.drawable.ic_custom_background}", Normal))
        products.add(Product("", "", "drawable://${R.drawable.ic_default_banner}", SpotLight))
    }
    products.add(Product("", "", "drawable://${R.drawable.ic_custom_background}", Special))
    return products
}