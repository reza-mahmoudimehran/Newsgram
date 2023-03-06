package ir.reza_mahmoudi.newsgram.feature_top_headlines.domain

import ir.reza_mahmoudi.newsgram.core.domain.news_list.entity.NewsListResponse
import retrofit2.Response

interface TopHeadlinesNewsRepository {
    suspend fun getTopHeadlinesNews(
        country: String?,
        category: String?,
        page: Int,
    ): Response<NewsListResponse>
}