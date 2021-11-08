package dev.m13d.shopinglist.domain

class GetShopItemInteractor(private val shopListRepository: ShopListRepository) {

    fun getShopItem(id: Int): ShopItem {
        return shopListRepository.getShopItem(id)
    }
}
