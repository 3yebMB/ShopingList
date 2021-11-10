package dev.m13d.shopinglist.presentation

import androidx.lifecycle.ViewModel
import dev.m13d.shopinglist.data.ShopListRepositoryImpl
import dev.m13d.shopinglist.domain.AddShopItemInteractor
import dev.m13d.shopinglist.domain.EditShopItemInteractor
import dev.m13d.shopinglist.domain.GetShopItemInteractor
import dev.m13d.shopinglist.domain.ShopItem
import java.lang.Exception

class ShopItemViewModel : ViewModel() {

    private val repository = ShopListRepositoryImpl

    private val getShopItemInteractor = GetShopItemInteractor(repository)
    private val editShopItemInteractor = EditShopItemInteractor(repository)
    private val addShopItemInteractor = AddShopItemInteractor(repository)

    fun getShopItem(shopItemId: Int) {
        val item = getShopItemInteractor.getShopItem(shopItemId)
    }

    fun addShopItem(txtName: String?, txtCount: String?) {
        val name = parseName(txtName)
        val count = parseCount(txtCount)
        val inputValid = validateInput(name, count)
        if (inputValid) {
            val shopItem = ShopItem(name, count, true)
            addShopItemInteractor.addShopItem(shopItem)
        }
    }

    fun editShopItem(txtName: String?, txtCount: String?) {
        val name = parseName(txtName)
        val count = parseCount(txtCount)
        val inputValid = validateInput(name, count)
        if (inputValid) {
            val shopItem = ShopItem(name, count, true)
            editShopItemInteractor.editShopItem(shopItem)
        }
    }

    private fun parseName(inputText: String?): String {
        return inputText?.trim() ?: ""
    }

    private fun parseCount(inputText: String?): Int {
        return try {
            inputText?.trim()?.toInt() ?: 0
        } catch (e: Exception) {
            0
        }
    }

    private fun validateInput(name: String, count: Int): Boolean {
        var result = true
        if (name.isBlank()) {
            // TODO: show error input name
            result = false
        }
        if (count <= 0) {
            // TODO: show error input count
            result = false
        }
        return result
    }
}

