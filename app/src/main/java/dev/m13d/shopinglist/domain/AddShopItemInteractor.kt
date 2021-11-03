package dev.m13d.shopinglist.domain

class AddShopItemInteractor(private val shopListRepository: ShopListRepository) {

    fun addShopItem(shopItem: ShopItem) {
        shopListRepository.addShopItem(shopItem)
    }
}
