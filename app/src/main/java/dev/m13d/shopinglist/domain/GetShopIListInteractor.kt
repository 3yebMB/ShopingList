package dev.m13d.shopinglist.domain

class GetShopIListInteractor(private val shopListRepository: ShopListRepository) {

    fun getShopList(): List<ShopItem> {
        return shopListRepository.getShopList()
    }
}
