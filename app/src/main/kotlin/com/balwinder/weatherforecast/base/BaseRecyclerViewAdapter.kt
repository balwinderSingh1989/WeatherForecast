package com.balwinder.weatherforecast.base

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter<T>(
    private val itemClickListener: ItemClickListener? = null,
    private val itemLongClickListener: ItemLongClickListener? = null
) :
    RecyclerView.Adapter<BaseVH<T>>() {

    //region BASE

    private var items = ArrayList<T>()

    //endregion

    //region BASE

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseVH<T> {

        val viewHolder =
            createBaseViewHolder(
                parent = parent,
                viewType = viewType
            )

        viewHolder.setItemClickListener(itemClickListener)
        viewHolder.setItemLongClickListener(itemLongClickListener)

        return viewHolder
    }

    override fun onBindViewHolder(
        holder: BaseVH<T>,
        position: Int
    ) = holder.bindItem(item = items[position])

    fun setItems(items: ArrayList<T>) {
        this.items = items
        notifyDataSetChanged()
    }

    fun getItems(): ArrayList<T> {
        val list = arrayListOf<T>()
        list.addAll(items)
        return list
    }

    fun updateItem(item: T, position: Int) {
        items[position] = item
        notifyItemChanged(position)
    }

    fun insertItem(item: T, position: Int) {
        items.add(position, item)
        notifyItemInserted(position)
    }

    fun insertItems(items: List<T>) {

        val previousSize = itemCount
        this.items.addAll(items)
        notifyItemRangeInserted(previousSize, itemCount)
    }

    fun removeItem(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    fun clearItems() {
        items.clear()
        notifyDataSetChanged()
    }

    fun isEmpty() {
        this.items.isEmpty()
    }

    //endregion

    //region ABSTRACT

    protected abstract fun createBaseViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseVH<T>

    //endregion


}