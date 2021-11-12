package dev.m13d.shopinglist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.m13d.shopinglist.data.ShopListRepositoryImpl
import dev.m13d.shopinglist.domain.AddShopItemInteractor
import dev.m13d.shopinglist.domain.EditShopItemInteractor
import dev.m13d.shopinglist.domain.GetShopItemInteractor
import dev.m13d.shopinglist.domain.ShopItem

class ShopItemViewModel : ViewModel() {

    private val repository = ShopListRepositoryImpl

    private val getShopItemInteractor = GetShopItemInteractor(repository)
    private val editShopItemInteractor = EditShopItemInteractor(repository)
    private val addShopItemInteractor = AddShopItemInteractor(repository)

    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName

    private val _errorInputCount = MutableLiveData<Boolean>()
    val errorInputCount: LiveData<Boolean>
        get() = _errorInputCount

    private val _shopItem = MutableLiveData<ShopItem>()
    val shopItem: LiveData<ShopItem>
        get() = _shopItem

    private val _shouldCloseScreen = MutableLiveData<Unit>()
    val shouldCloseScreen: LiveData<Unit>
        get() = _shouldCloseScreen

    fun getShopItem(shopItemId: Int) {
        val item = getShopItemInteractor.getShopItem(shopItemId)
        _shopItem.value = item
    }

    fun addShopItem(txtName: String?, txtCount: String?) {
        val name = parseName(txtName)
        val count = parseCount(txtCount)
        val inputValid = validateInput(name, count)
        if (inputValid) {
            val shopItem = ShopItem(name, count, true)
            addShopItemInteractor.addShopItem(shopItem)
            finishWork()
        }
    }

    fun editShopItem(txtName: String?, txtCount: String?) {
        val name = parseName(txtName)
        val count = parseCount(txtCount)
        val inputValid = validateInput(name, count)
        if (inputValid) {
            _shopItem.value?.let {
                val item = it.copy(name = name, count = count)
                editShopItemInteractor.editShopItem(item)
                finishWork()
            }
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
            _errorInputName.value = true
            result = false
        }
        if (count <= 0) {
            _errorInputCount.value = true
            result = false
        }
        return result
    }

    fun resetErrorInputName() {
        _errorInputName.value = false
    }

    fun resetErrorInputCount() {
        _errorInputCount.value = false
    }

    private fun finishWork() {
        _shouldCloseScreen.value = Unit
    }
}
