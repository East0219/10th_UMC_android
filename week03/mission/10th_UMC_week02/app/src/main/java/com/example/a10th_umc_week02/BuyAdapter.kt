package com.example.a10th_umc_week02

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a10th_umc_week02.databinding.ItemBuyBinding

class BuyAdapter(
    private var productList: MutableList<HomeData>,
    private val onVisitClicked: (HomeData) -> Unit
) : RecyclerView.Adapter<BuyAdapter.BuyViewHolder>() {

    class BuyViewHolder(val binding: ItemBuyBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(home: HomeData) {
            binding.tvProduct3.setImageResource(home.image)
            binding.tvProductName3.text = home.name
            binding.tvProductPrice3.text = home.price
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuyViewHolder {
        val binding = ItemBuyBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BuyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BuyViewHolder, position: Int) {
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