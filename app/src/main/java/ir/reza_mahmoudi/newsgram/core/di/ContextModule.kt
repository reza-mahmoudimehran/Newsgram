package io.fajarca.core.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.reza_mahmoudi.newsgram.core.di.qualifiers.DefaultDispatcher
import ir.reza_mahmoudi.newsgram.core.di.qualifiers.IoDispatcher
import ir.reza_mahmoudi.newsgram.core.di.qualifiers.MainDispatcher
import ir.reza_mahmoudi.newsgram.core.di.qualifiers.MainImmediateDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ContextModule {

    @Provides
    @Singleton
    fun provideContext(app: Application): Context = app
}
