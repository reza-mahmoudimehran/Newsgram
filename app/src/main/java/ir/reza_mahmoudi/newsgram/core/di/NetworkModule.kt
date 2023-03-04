package ir.reza_mahmoudi.newsgram.core.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.reza_mahmoudi.newsgram.core.data.interceptor.HeaderInterceptor
import ir.reza_mahmoudi.newsgram.core.util.constants.ApiConstants.Companion.BASE_URL
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpCache(context: Context): Cache {
        val cacheSize: Long = 10 * 10 * 1024
        return Cache(context.cacheDir, cacheSize)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        cache: Cache,
        headerInterceptor: HeaderInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .cache(cache)
            .addInterceptor(headerInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
    }
}