package com.example.a10th_umc_week07.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a10th_umc_week07.R
import com.example.a10th_umc_week07.data.model.HomeData
import com.example.a10th_umc_week07.di.DataStoreModule
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

data class BuyItemState(val product: HomeData, val isFavorite: Boolean)

@HiltViewModel
class BuyViewModel @Inject constructor(
    private val dataStoreModule: DataStoreModule
) : ViewModel() {

    private val _allProducts = MutableStateFlow(
        listOf(
            HomeData(
                id = 1,
                name = "Air Jordan XXXVI",
                category = "Men's Shoes",
                colours = 1,
                price = "US$185",
                image = R.drawable.ic_blackshoes
            ),
            HomeData(
                id = 2,
                name = "Nike Air Force 1 '07",
                category = "Men's Shoes",
                colours = 2,
                price = "US$115",
                image = R.drawable.ic_whiteshoes
            )
        )
    )

    private val _buyList = MutableStateFlow<List<BuyItemState>>(emptyList())
    val buyList: StateFlow<List<BuyItemState>> = _buyList

    init {
        observeWishList()
    }

    private fun observeWishList() {
        viewModelScope.launch {
            combine(_allProducts, dataStoreModule.getName()) { all, wish ->
                all.map { product ->
                    BuyItemState(
                        product = product,
                        isFavorite = wish.any { it.name == product.name }
                    )
                }
            }.collect { combinedList ->
                _buyList.value = combinedList
            }
        }
    }

    fun toggleWishList(product: HomeData) {
        viewModelScope.launch {
            val currentWishList = dataStoreModule.getName().first().toMutableList()
            val existingItem = currentWishList.find { it.name == product.name }

            if (existingItem != null) {
                currentWishList.removeAll { it.name == product.name }
            } else {
                currentWishList.add(product)
            }
            dataStoreModule.saveName(currentWishList)
        }
    }
}