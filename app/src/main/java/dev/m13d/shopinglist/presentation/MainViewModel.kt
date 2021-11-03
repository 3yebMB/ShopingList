package dev.m13d.shopinglist.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.m13d.shopinglist.data.ShopListRepositoryImpl
import dev.m13d.shopinglist.domain.DeleteShopItemInteractor
import dev.m13d.shopinglist.domain.EditShopItemInteractor
import dev.m13d.shopinglist.domain.GetShopIListInteractor
import dev.m13d.shopinglist.domain.ShopItem

class MainViewModel: ViewModel() {

    private val repository = ShopListRepositoryImpl

    private val getShopListInteractor = GetShopIListInteractor(repository)
    private val editShopItemInteractor = EditShopItemInteractor(repository)
    private val deleteShopItemInteractor = DeleteShopItemInteractor(repository)

    val shopList = MutableLiveData<List<ShopItem>>()

    fun getShopList() {
        val list = getShopListInteractor.getShopList()
        shopList.value = list
    }

    fun deleteShopItem(shopItem: ShopItem) {
        deleteShopItemInteractor.deleteShopItem(shopItem)
        getShopList()
    }

    fun changeEnableState(shopItem: ShopItem) {
        val newItem = shopItem.copy(available = !shopItem.available)
        editShopItemInteractor.editShopItem(newItem)
        getShopList()
    }
}
