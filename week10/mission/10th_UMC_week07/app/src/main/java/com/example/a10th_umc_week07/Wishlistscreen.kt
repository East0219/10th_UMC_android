package com.example.a10th_umc_week07

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WishlistScreen(onProductClick: (HomeData) -> Unit) {
    val products = remember {
        listOf(
            HomeData("Air Jordan XXXVI",    "US$185", R.drawable.ic_blackshoes),
            HomeData("Nike Air Force 1 '07", "US$115", R.drawable.ic_whiteshoes)
        )
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "위시리스트",
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            modifier = Modifier.padding(start = 24.dp, top = 44.dp, bottom = 8.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(0.dp),
            horizontalArrangement = Arrangement.spacedBy(0.dp)
        ) {
            items(products) { product ->
                ProductCard(
                    product = product,
                    onClick = { onProductClick(product) }
                )
            }
        }
    }
}