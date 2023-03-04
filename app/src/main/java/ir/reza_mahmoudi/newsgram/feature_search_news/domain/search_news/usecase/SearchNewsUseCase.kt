package ir.reza_mahmoudi.newsgram.feature_search_news.domain.search_news.usecase

import ir.reza_mahmoudi.newsgram.core.di.qualifiers.IoDispatcher
import ir.reza_mahmoudi.newsgram.core.domain.common.usecase.ApiUseCase
import ir.reza_mahmoudi.newsgram.core.domain.news_list.entity.NewsListResponse
import ir.reza_mahmoudi.newsgram.feature_search_news.domain.SearchNewsRepository
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Response
import javax.inject.Inject

class SearchNewsUseCase @Inject constructor(
    private val searchNewsRepository: SearchNewsRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : ApiUseCase<SearchNewsUseCase.Params, NewsListResponse>(
    ioDispatcher
) {
    override suspend fun execute(parameters: Params): Response<NewsListResponse> =
        searchNewsRepository.searchNews(parameters.query,parameters.language,parameters.sortBy,parameters.page)

    data class Params(
        val query: String?,
        val language: String?,
        val sortBy: String?,
        val page: Int,
    )
}
