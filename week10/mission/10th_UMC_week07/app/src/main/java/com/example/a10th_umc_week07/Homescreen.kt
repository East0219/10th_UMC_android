package com.example.a10th_umc_week07

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(onProductClick: (HomeData) -> Unit) {
    val products = remember {
        listOf(
            HomeData("Air Jordan XXXVI",   "US$185", R.drawable.ic_blackshoes),
            HomeData("Nike Air Force 1 '07", "US$115", R.drawable.ic_whiteshoes)
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Discover",
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            modifier = Modifier.padding(start = 41.dp, top = 50.dp)
        )
        Text(
            text = "9월 4일 목요일",
            fontSize = 16.sp,
            modifier = Modifier.padding(start = 41.dp, top = 4.dp)
        )

        Image(
            painter = painterResource(id = R.mipmap.ic_nikehome),
            contentDescription = "Nike Home",
            modifier = Modifier
                .size(300.dp)
                .align(Alignment.CenterHorizontally)
                .padding(top = 16.dp)
        )

        Text(
            text = "What's new?",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier.padding(start = 42.dp, top = 8.dp)
        )
        Text(
            text = "나이키 최신 상품",
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            color = Color(0xFF767676),
            modifier = Modifier.padding(start = 42.dp, top = 12.dp)
        )

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(products) { product ->
                ProductCard(
                    product = product,
                    imageSize = 200.dp,
                    onClick = { onProductClick(product) }
                )
            }
        }
    }
}