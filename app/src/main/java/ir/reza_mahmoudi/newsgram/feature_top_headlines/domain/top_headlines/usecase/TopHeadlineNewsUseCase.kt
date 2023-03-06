package ir.reza_mahmoudi.newsgram.feature_top_headlines.domain.top_headlines.usecase

import ir.reza_mahmoudi.newsgram.core.di.qualifiers.IoDispatcher
import ir.reza_mahmoudi.newsgram.core.domain.common.usecase.ApiUseCase
import ir.reza_mahmoudi.newsgram.core.domain.news_list.entity.NewsListResponse
import ir.reza_mahmoudi.newsgram.feature_top_headlines.domain.TopHeadlinesNewsRepository
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Response
import javax.inject.Inject

class TopHeadlineNewsUseCase @Inject constructor(
    private val topHeadlinesNewsRepository: TopHeadlinesNewsRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : ApiUseCase<TopHeadlineNewsUseCase.Params, NewsListResponse>(
    ioDispatcher
) {
    override suspend fun execute(parameters: Params): Response<NewsListResponse> =
        topHeadlinesNewsRepository.getTopHeadlinesNews(
            parameters.country,
            parameters.category,
            parameters.page
        )

    data class Params(
        val country: String?,
        val category: String?,
        val page: Int,
    )
}
