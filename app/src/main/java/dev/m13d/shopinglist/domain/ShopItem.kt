package dev.m13d.shopinglist.domain

data class ShopItem(
    val id: Int,
    val name: String,
    val count: Int,
    val available: Boolean
)
