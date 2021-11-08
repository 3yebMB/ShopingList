package dev.m13d.shopinglist.domain

class EditShopItemInteractor(private val shopListRepository: ShopListRepository) {

    fun editShopItem(shopItem: ShopItem) {
        shopListRepository.editShopItem(shopItem)
    }
}
