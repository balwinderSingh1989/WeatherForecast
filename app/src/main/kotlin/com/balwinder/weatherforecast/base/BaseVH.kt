package com.balwinder.weatherforecast.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer

abstract class BaseVH<T> :
    RecyclerView.ViewHolder, View.OnClickListener,
    View.OnLongClickListener, LayoutContainer {

    override val containerView = itemView

    //region VARIABLES

    private var itemClickListener: ItemClickListener? = null
    private var itemLongClickListener: ItemLongClickListener? = null

    constructor(bindingView: View) : super(bindingView)


    //endregion

    //region ABSTRACT

    abstract fun bindItem(item: T)

    //endregion

    //region LISTENERS

    fun setItemClickListener(itemClickListener: ItemClickListener?) {

        itemClickListener?.let {
            this.itemClickListener = itemClickListener
            itemView.setOnClickListener(this)
        }
    }

    fun setItemLongClickListener(itemLongClickListener: ItemLongClickListener?) {
        itemLongClickListener?.let {
            this.itemLongClickListener = itemLongClickListener
            itemView.setOnLongClickListener(this)
        }
    }

    override fun onClick(v: View?) {

        itemClickListener?.onItemClick(
            position = adapterPosition,
            view = itemView
        )
    }

    override fun onLongClick(v: View?): Boolean {

        itemLongClickListener?.onItemLongClicked(
            position = adapterPosition,
            view = itemView
        )
        return true
    }

    //endregion

}