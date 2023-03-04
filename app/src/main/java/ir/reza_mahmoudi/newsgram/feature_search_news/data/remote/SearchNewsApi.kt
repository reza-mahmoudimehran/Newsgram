package ir.reza_mahmoudi.newsgram.feature_search_news.data.remote

import ir.reza_mahmoudi.newsgram.core.domain.news_list.entity.NewsListResponse
import ir.reza_mahmoudi.newsgram.core.util.constants.ApiConstants.Companion.PAGINATION_PAGE_SIZE
import retrofit2.Response
import retrofit2.http.*

interface SearchNewsApi {

    @GET("v2/everything")
    suspend fun searchNews(
        @Query("q") query: String?,
        @Query("language") language: String?,
        @Query("sortBy") sortBy: String?,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int = PAGINATION_PAGE_SIZE
    ): Response<NewsListResponse>

}