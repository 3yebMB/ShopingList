package dev.m13d.shopinglist.domain

import androidx.lifecycle.LiveData

class GetShopIListInteractor(private val shopListRepository: ShopListRepository) {

    fun getShopList(): LiveData<List<ShopItem>> {
        return shopListRepository.getShopList()
    }
}
