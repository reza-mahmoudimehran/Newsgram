package ir.reza_mahmoudi.newsgram.feature_search_news.data.repository

import ir.reza_mahmoudi.newsgram.core.domain.news_list.entity.NewsListResponse
import ir.reza_mahmoudi.newsgram.feature_search_news.data.remote.SearchNewsApi
import ir.reza_mahmoudi.newsgram.feature_search_news.domain.SearchNewsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class SearchNewsRepositoryImpl @Inject constructor(
    private val searchNewsApi: SearchNewsApi,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : SearchNewsRepository {

    override suspend fun searchNews(
        query: String?,
        language: String?,
        sortBy: String?,
        page: Int
    ): Response<NewsListResponse> =
        withContext(ioDispatcher) {
            searchNewsApi.searchNews(
                query = query,
                language = language,
                sortBy = sortBy,
                page = page
            )
        }
}