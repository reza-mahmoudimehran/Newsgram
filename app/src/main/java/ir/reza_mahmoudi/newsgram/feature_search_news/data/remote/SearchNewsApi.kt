package ir.reza_mahmoudi.newsgram.feature_search_news.data.remote

import ir.reza_mahmoudi.newsgram.core.domain.news_list.entity.NewsListResponse
import retrofit2.http.*

interface SearchNewsApi {

    @GET("v2/everything")
    suspend fun getNews(
        @Query("q") query: String?,
        @Query("language") language: String?,
        @Query("sortBy") sortBy: String?,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): NewsListResponse

}