package com.itl.kg.app.tainanopendatademo.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.itl.kg.app.tainanopendatademo.databinding.ListParkingItemBinding
import com.itl.kg.app.tainanopendatademo.module.unit.ParkingResp


class ParkingListAdapter(
        val list: MutableList<ParkingResp>
) : RecyclerView.Adapter<ParkingListAdapter.ParkingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParkingViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ParkingViewHolder(ListParkingItemBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ParkingViewHolder, position: Int) {
        holder.bind(list[position])
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