package com.example.a10th_umc_week07.data.remote

import com.example.a10th_umc_week07.di.DataStoreModule
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val dataStoreModule: DataStoreModule
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val requestBuilder = chain.request().newBuilder()

        val apiKey = "reqres_c7dd2e08f6484752b491d89202a23448"

        if (apiKey.isNotEmpty()) {
            requestBuilder.addHeader("x-api-key", apiKey)
        }

        return chain.proceed(requestBuilder.build())
    }
}