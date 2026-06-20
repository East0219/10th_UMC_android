package com.example.a10th_umc_week07.data.model

import com.google.gson.annotations.SerializedName

data class UserResponse(
    val data: UserData
)

data class FollowingList(
    val data: List<UserData>
)

data class UserData(
    val id: Int,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    val avatar: String
)