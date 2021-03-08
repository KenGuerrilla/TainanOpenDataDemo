package com.itl.kg.app.tainanopendatademo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itl.kg.app.tainanopendatademo.databinding.ListItemMainBinding


class MainListAdapter(private val list: List<MainListItem>) : RecyclerView.Adapter<MainListAdapter.MainListViewHolder>() {

    var itemClickListener: ItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainListViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        return MainListViewHolder(ListItemMainBinding.inflate(inflate, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MainListViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            itemClickListener?.onItemClick(item, holder.itemView)
        }
    }

    inner class MainListViewHolder(private val itemBinding: ListItemMainBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(mainListItem: MainListItem) {
            itemBinding.mMainListTitleTv.text = mainListItem.title
            itemBinding.mMainListDescTv.text = mainListItem.desc
        }
    }

}

interface ItemClickListener {
    fun onItemClick(item: MainListItem, view: View)
}