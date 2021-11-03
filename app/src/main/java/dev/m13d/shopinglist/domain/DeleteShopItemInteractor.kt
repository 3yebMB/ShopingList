package dev.m13d.shopinglist.domain

class DeleteShopItemInteractor(private val shopListRepository: ShopListRepository) {

    fun deleteShopItem(shopItem: ShopItem) {
        shopListRepository.deleteShopItem(shopItem)
    }
}
