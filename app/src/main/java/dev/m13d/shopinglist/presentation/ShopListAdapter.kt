package dev.m13d.shopinglist.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import dev.m13d.shopinglist.R
import dev.m13d.shopinglist.domain.ShopItem

class ShopListAdapter : ListAdapter<ShopItem, ShopItemViewHolder>(ShopItemDiffCallback()) {

    var onShopItemLongClickListener: ((ShopItem) -> Unit)? = null
    var onShopItemClickListener: ((ShopItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val layout = when (viewType) {
            VIEW_TYPE_ENABLED -> R.layout.item_shop_enabled
            VIEW_TYPE_DISABLED -> R.layout.item_shop_disabled
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ShopItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = getItem(position)
        holder.tvName.text = shopItem.name
        holder.tvCount.text = shopItem.count.toString()
        holder.view.setOnClickListener {
            onShopItemClickListener?.invoke(shopItem)
        }
        holder.view.setOnLongClickListener {
            onShopItemLongClickListener?.invoke(shopItem)
            true
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if (item.available) VIEW_TYPE_ENABLED else VIEW_TYPE_DISABLED
    }

    companion object {

        const val VIEW_TYPE_ENABLED = 666
        const val VIEW_TYPE_DISABLED = 999

        const val MAX_POOL_SIZE = 11
    }
}
