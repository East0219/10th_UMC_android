package com.example.a10th_umc_week07.data.remote

import com.example.a10th_umc_week07.data.model.UserData
import com.example.a10th_umc_week07.domain.repository.AuthListResponse
import com.example.a10th_umc_week07.domain.repository.AuthResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("api/users/1")
    suspend fun getMyInfo(): Response<AuthResponse<UserData>>

    @GET("api/users?page=2")
    suspend fun getFollowingList(): Response<AuthListResponse<UserData>>
}