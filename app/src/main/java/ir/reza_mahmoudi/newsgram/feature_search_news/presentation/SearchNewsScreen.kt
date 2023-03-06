package ir.reza_mahmoudi.newsgram.feature_search_news.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import ir.reza_mahmoudi.newsgram.R
import ir.reza_mahmoudi.newsgram.core.presentation.app_components.filter.FiltersList
import ir.reza_mahmoudi.newsgram.core.presentation.app_components.news.NewsItem
import ir.reza_mahmoudi.newsgram.core.presentation.compose_components.paging_state.PagingLoadingState
import ir.reza_mahmoudi.newsgram.core.presentation.compose_components.search_box.SearchBox
import ir.reza_mahmoudi.newsgram.core.presentation.design_system.theme.NewsgramColors
import ir.reza_mahmoudi.newsgram.core.presentation.design_system.theme.NewsgramTypography
import ir.reza_mahmoudi.newsgram.core.util.list.languageItems
import ir.reza_mahmoudi.newsgram.core.util.list.sortByItems

@Composable
fun SearchNewsScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchNewsViewModel = hiltViewModel()
) {
    val query: MutableState<String?> = remember { mutableStateOf(null) }
    val language: MutableState<String?> = remember { mutableStateOf(null) }
    val sortBy: MutableState<String?> = remember { mutableStateOf("publishedAt") }

    val languageList = remember { languageItems }
    val sortByList = remember { sortByItems }

    val searchList =
        viewModel.searchNews(query = query.value, language = language.value, sortBy = sortBy.value)
            .collectAsLazyPagingItems()


    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .padding(5.dp)
                .wrapContentHeight()
                .wrapContentWidth()
                .padding(8.dp),
            text = stringResource(id = R.string.search),
            color = NewsgramColors.designSystem.PrimaryText,
            style = NewsgramTypography.text18
        )
        LazyColumn {
            item {
                SearchBox(text = query)
            }
            item {
                FiltersList(
                    filterText = stringResource(id = R.string.language),
                    filterState = language,
                    filterList = languageList
                )
            }
            item {
                FiltersList(
                    filterText = stringResource(id = R.string.sort_by),
                    filterState = sortBy,
                    filterList = sortByList
                )
            }

            if (searchList.itemCount > 0) {
                item {
                    Text(
                        modifier = Modifier
                            .padding(10.dp)
                            .wrapContentHeight()
                            .wrapContentWidth()
                            .padding(8.dp),
                        text = stringResource(id = R.string.result),
                        color = NewsgramColors.designSystem.Neutral30,
                        style = NewsgramTypography.text14
                    )
                }
                items(searchList.itemCount) {
                    NewsItem(article = searchList[it])
                }
            }

            item {
                PagingLoadingState(pagingItems = searchList)
            }
        }
    }
}