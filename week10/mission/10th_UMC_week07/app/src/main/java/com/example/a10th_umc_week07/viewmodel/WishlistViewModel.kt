package com.example.a10th_umc_week07.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a10th_umc_week07.data.model.HomeData
import com.example.a10th_umc_week07.di.DataStoreModule
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.collections.toMutableList

@HiltViewModel
class WishlistViewModel @Inject constructor(
    private val dataStoreModule: DataStoreModule
) : ViewModel() {

    private val _wishList = MutableStateFlow<List<HomeData>>(emptyList())
    val wishList: StateFlow<List<HomeData>> = _wishList

    fun loadWishList() {
        viewModelScope.launch {
            dataStoreModule.getName().collect { list ->
                _wishList.value = list
            }
        }
    }

    fun removeItem(item: HomeData) {
        viewModelScope.launch {
            val currentList = _wishList.value.toMutableList()
            currentList.removeIf { it.name == item.name }
            dataStoreModule.saveName(currentList)
        }
    }
}