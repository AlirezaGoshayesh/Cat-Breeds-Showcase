package com.sample.cats.data.remote.connection

import com.sample.cats.BuildConfig
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class TokenInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
            .newBuilder()
            .header("accept", "application/json")
            .header("x-api-key", BuildConfig.API_KEY)
            .build()
        return chain.proceed(request)
    }
}
