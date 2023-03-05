package ir.reza_mahmoudi.newsgram.feature_top_headlines.data.remote.source

import ir.reza_mahmoudi.newsgram.core.domain.news_items.entity.Article
import ir.reza_mahmoudi.newsgram.core.util.network.ApiResult
import androidx.paging.PagingSource
import androidx.paging.PagingState
import ir.reza_mahmoudi.newsgram.feature_top_headlines.domain.top_headlines.usecase.TopHeadlineNewsUseCase

class TopHeadlineNewsSource(
    private val topHeadlineNewsUseCase: TopHeadlineNewsUseCase,
    private val country: String?,
    private val category: String?,
) : PagingSource<Int, Article>() {

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val page = params.key ?: 1
            val response = topHeadlineNewsUseCase(
                TopHeadlineNewsUseCase.Params(
                    country = country,
                    category = category,
                    page = page
                )
            )

            when (response) {
                is ApiResult.ServerError -> {
                    LoadResult.Error(Exception(response.errorBody.message))
                }
                is ApiResult.Error -> {
                    LoadResult.Error(Exception(response.exception.message))
                }
                is ApiResult.Success -> {
                    LoadResult.Page(
                        data = response.data.articles,
                        prevKey = if (page == 1) null else page.minus(1),
                        nextKey = if (response.data.articles.isEmpty()) null else page.plus(1),
                    )
                }
                else -> {
                    LoadResult.Error(Exception("Loading"))
                }
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}