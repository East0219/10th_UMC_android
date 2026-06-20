package com.example.a10th_umc_week07.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a10th_umc_week07.data.model.UserData
import com.example.a10th_umc_week07.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FollowingViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _myInfo = MutableStateFlow<UserData?>(null)
    val myInfo: StateFlow<UserData?> = _myInfo

    private val _followingList = MutableStateFlow<List<UserData>>(emptyList())
    val followingList: StateFlow<List<UserData>> = _followingList

    fun loadData() {
        viewModelScope.launch {
            repository.Login().onSuccess { user ->
                _myInfo.value = user
            }
            repository.getFollowingList().onSuccess { list ->
                _followingList.value = list
            }
        }
    }
}