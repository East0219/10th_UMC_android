package com.example.a10th_umc_week07.data.model

data class HomeData(
    val id: Int,
    val name: String,
    val category: String,
    val colours: Int,
    val price: String,
    val image: Int,
    val isBestSeller: Boolean = false,
    val isFavorite: Boolean = false
)