package ir.reza_mahmoudi.newsgram.feature_search_news.domain

import ir.reza_mahmoudi.newsgram.core.domain.news_list.entity.NewsListResponse
import retrofit2.Response

interface SearchNewsRepository {
    suspend fun searchNews(
        query: String?,
        language: String?,
        sortBy: String?,
        page: Int,
    ) : Response<NewsListResponse>
}