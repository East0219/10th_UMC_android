package com.example.a10th_umc_week07.domain.repository

data class AuthResponse<T>(
    val data: T?
)

data class AuthListResponse<T>(
    val data: List<T>?
)