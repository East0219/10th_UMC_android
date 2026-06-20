package com.example.a10th_umc_week07.domain.repository

import android.util.Log
import com.example.a10th_umc_week07.data.model.UserData
import com.example.a10th_umc_week07.data.remote.ApiService

class AuthRepository(private val service: ApiService) {

    suspend fun Login(userId: Int = 2): Result<UserData> = try {
        val response = service.getMyInfo()

        if (response.isSuccessful) {
            val body = response.body()

            if (body?.data != null) {
                Log.d("tag", "OK")
                Result.success(body.data)
            } else {
                Log.d("tag", "Response OK but Data is null")
                Result.failure(RuntimeException("Response OK but Data is null"))
            }
        } else {
            val errMsg = response.errorBody()?.string() ?: response.message()
            Log.d("tag", "비상: $errMsg")
            Result.failure(RuntimeException("HTTP ${response.code()}: $errMsg"))
        }
    } catch (e: Exception) {
        Log.e("tag", "Network Error", e)
        Result.failure(e)
    }

    suspend fun getFollowingList(): Result<List<UserData>> = try {
        val response = service.getFollowingList()
        if (response.isSuccessful) {
            Result.success(response.body()?.data ?: listOf())
        } else {
            val errMsg = response.errorBody()?.string() ?: response.message()
            Result.failure(RuntimeException("Error ${response.code()}: $errMsg"))
        }
    } catch (e: Exception) {
        Result.failure(e)
    }
}