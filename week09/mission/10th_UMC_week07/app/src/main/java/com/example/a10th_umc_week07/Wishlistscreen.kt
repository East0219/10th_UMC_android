package com.example.a10th_umc_week07

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.a10th_umc_week07.data.model.HomeData
import com.example.a10th_umc_week07.viewmodel.WishlistViewModel

@Composable
fun WishlistScreen(
    onProductClick: (HomeData) -> Unit,
    viewModel: WishlistViewModel = hiltViewModel()
) {
    val wishList by viewModel.wishList.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadWishList()
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "위시리스트",
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            modifier = Modifier.padding(start = 24.dp, top = 44.dp, bottom = 8.dp)
        )

        if (wishList.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "위시리스트가 비어 있습니다.", color = Color.Gray)
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize()
            ) {
                items(wishList) { product ->
                    ProductCard(
                        product = product,
                        showHeart = true,
                        isFavorite = true,
                        onHeartClick = {
                            viewModel.removeItem(product)
                        },
                        onClick = { onProductClick(product) }
                    )
                }
            }
        }
    }
}