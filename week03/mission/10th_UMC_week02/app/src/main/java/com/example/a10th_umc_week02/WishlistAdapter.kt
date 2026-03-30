package com.example.a10th_umc_week02

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a10th_umc_week02.databinding.ItemWishlistBinding

class WishlistAdapter(
    private var productList: MutableList<HomeData>,
    private val onVisitClicked: (HomeData) -> Unit
) : RecyclerView.Adapter<WishlistAdapter.WishlistViewHolder>() {

    class WishlistViewHolder(val binding: ItemWishlistBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(home: HomeData) {
            binding.tvProduct2.setImageResource(home.image)
            binding.tvProductName2.text = home.name
            binding.tvProductPrice2.text = home.price
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishlistViewHolder {
        val binding = ItemWishlistBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return WishlistViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WishlistViewHolder, position: Int) {
        val product = productList[position]
        holder.bind(product)

        holder.itemView.setOnClickListener {
            onVisitClicked(product)
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}