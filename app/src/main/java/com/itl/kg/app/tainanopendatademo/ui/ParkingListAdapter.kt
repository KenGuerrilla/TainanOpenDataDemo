package com.itl.kg.app.tainanopendatademo.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itl.kg.app.tainanopendatademo.databinding.ListParkingItemBinding
import com.itl.kg.app.tainanopendatademo.unit.ParkingResp


class ParkingListAdapter(
        val list: MutableList<ParkingResp>
) : RecyclerView.Adapter<ParkingListAdapter.ParkingViewHolder>() {

    companion object {
        const val TAG = "ParkingListAdapter"
    }

    private var itemClickListener: ItemClickListener? = null

    fun setOnItemClick(listener: ItemClickListener) {
        this.itemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParkingViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ParkingViewHolder(ListParkingItemBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ParkingViewHolder, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener {
            itemClickListener?.onItemClick(it, list[position])
        }
    }

    inner class ParkingViewHolder(
            private val itemBinding: ListParkingItemBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(resp: ParkingResp) {
            itemBinding.mParkingItemTitleTv.text = resp.name
            itemBinding.mParkingItemAddressTv.text = resp.address
        }
    }
}

interface ItemClickListener {
    fun onItemClick(view: View, item: ParkingResp)
}