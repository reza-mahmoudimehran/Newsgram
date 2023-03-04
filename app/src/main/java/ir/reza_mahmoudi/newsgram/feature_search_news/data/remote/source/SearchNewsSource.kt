package ir.reza_mahmoudi.newsgram.feature_search_news.data.remote.source

import ir.reza_mahmoudi.newsgram.core.domain.news_items.entity.Article
import ir.reza_mahmoudi.newsgram.core.util.network.ApiResult
import ir.reza_mahmoudi.newsgram.feature_search_news.domain.search_news.usecase.SearchNewsUseCase
import androidx.paging.PagingSource
import androidx.paging.PagingState


class SearchNewsSource(
    private val searchNewsUseCase: SearchNewsUseCase,
    private val query: String?,
    private val language: String?,
    private val sortBy: String?,
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
            val response = searchNewsUseCase(
                SearchNewsUseCase.Params(
                    query = query,
                    language = language,
                    sortBy = sortBy,
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