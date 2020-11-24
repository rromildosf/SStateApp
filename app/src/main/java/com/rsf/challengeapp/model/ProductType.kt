package com.rsf.challengeapp.model

sealed class ProductType
object SpotLight: ProductType()
object Normal: ProductType()
object Special: ProductType()