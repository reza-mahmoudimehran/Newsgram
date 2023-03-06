package ir.reza_mahmoudi.newsgram.core.presentation.compose_components.paging_state

import androidx.compose.runtime.Composable
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems


@Composable
fun <T : Any> PagingLoadingState(
    pagingItems: LazyPagingItems<T>,
) {
    pagingItems.loadState.apply {
        when {
            refresh is LoadState.Loading -> {
                LoadingComponent(PagingState.LOADING)
            }
            append is LoadState.Loading -> {
                LoadingComponent(PagingState.LOADING)
            }
            append is LoadState.NotLoading -> {
                LoadingComponent(PagingState.FINISHED)
            }
            append is LoadState.Error -> {
                LoadingComponent(PagingState.ERROR)
            }
            refresh is LoadState.NotLoading -> {
                LoadingComponent(PagingState.FINISHED)
            }
            refresh is LoadState.Error -> {
                LoadingComponent(PagingState.ERROR)
            }
        }
    }
}