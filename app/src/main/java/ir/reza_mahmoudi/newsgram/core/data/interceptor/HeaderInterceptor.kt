package ir.reza_mahmoudi.newsgram.core.data.interceptor

import ir.reza_mahmoudi.newsgram.core.util.constants.ApiConstants.Companion.API_KEY
import okhttp3.Interceptor
import okhttp3.Response

class  HeaderInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val chainBuilder = chain.request().newBuilder()
        chainBuilder.addHeader("content-type", "application/json")
        chainBuilder.addHeader("Authorization", API_KEY)
        return chain.proceed(chainBuilder.build())
    }
}