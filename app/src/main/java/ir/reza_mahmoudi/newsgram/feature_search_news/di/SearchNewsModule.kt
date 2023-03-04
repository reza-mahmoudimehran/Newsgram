package ir.reza_mahmoudi.newsgram.feature_search_news.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.reza_mahmoudi.newsgram.feature_search_news.data.remote.SearchNewsApi
import ir.reza_mahmoudi.newsgram.feature_search_news.data.repository.SearchNewsRepositoryImpl
import ir.reza_mahmoudi.newsgram.feature_search_news.domain.SearchNewsRepository
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SearchNewsModule {

    @Singleton
    @Provides
    fun providesSearchNewsRepository(searchNewsApi: SearchNewsApi): SearchNewsRepository =
        SearchNewsRepositoryImpl(searchNewsApi)

    @Singleton
    @Provides
    fun provideSearchNewsApi(retrofit: Retrofit): SearchNewsApi =
        retrofit.create(SearchNewsApi::class.java)
}