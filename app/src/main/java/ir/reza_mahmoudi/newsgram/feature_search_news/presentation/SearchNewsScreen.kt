package ir.reza_mahmoudi.newsgram.feature_search_news.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import ir.reza_mahmoudi.newsgram.R
import ir.reza_mahmoudi.newsgram.core.domain.news_items.entity.Article
import ir.reza_mahmoudi.newsgram.core.presentation.compose_components.image.AsyncImage
import ir.reza_mahmoudi.newsgram.core.presentation.compose_components.search_box.SearchBox
import ir.reza_mahmoudi.newsgram.core.presentation.design_system.shapes.*
import ir.reza_mahmoudi.newsgram.core.presentation.design_system.theme.NewsgramColors
import ir.reza_mahmoudi.newsgram.core.presentation.design_system.theme.NewsgramTypography
import ir.reza_mahmoudi.newsgram.core.util.time.toLocalTime
import java.util.*
import kotlin.text.Typography

@Composable
fun SearchNewsScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchNewsViewModel = hiltViewModel()
) {
    val query: MutableState<String?> = remember { mutableStateOf(null) }
    val language: MutableState<String?> = remember { mutableStateOf(null) }
    val sortBy: MutableState<String?> = remember { mutableStateOf("publishedAt") }

    val languageList = remember {
        mutableStateOf(
            mapOf(
                null to "All",
                "en" to "English",
                "de" to "German",
                "es" to "Spanish",
                "fr" to "French",
                "it" to "Italian",
                "no" to "Norwegian",
                "pt" to "Portuguese",
                "ru" to "Russian",
                "sv" to "Swedish",
                "ar" to "Arabic",
                "zh" to "Chinese"
            )
        )
    }
    val sortByList = remember {
        mutableStateOf(
            mapOf(
                "publishedAt" to "Published At",
                "relevancy" to "Relevancy",
                "popularity" to "Popularity"
            )
        )
    }
//    val search = remember {  mutableStateOf("text") }

    val searchList =
        viewModel.searchNews(query = query.value, language = language.value, sortBy = sortBy.value)
            .collectAsLazyPagingItems()

    LazyColumn(modifier = modifier.background(
        color = MaterialTheme.NewsgramColors.designSystem.PrimaryBackground,
    )) {
        item {
            SearchBox(text = query)
        }
        item {
            LazyRow(
                modifier = Modifier
                    .padding(5.dp)
            ) {
                item {
                    Text(
                        modifier = Modifier
                            .padding(5.dp)
                            .wrapContentHeight()
                            .wrapContentWidth()
                            .padding(8.dp),
                        text = stringResource(id = R.string.language),
                        color = MaterialTheme.NewsgramColors.designSystem.PrimaryText,
                        style = MaterialTheme.NewsgramTypography.text14
                    )
                }

                languageList.value.forEach {
                    item {
                        Text(
                            modifier = Modifier
                                .padding(5.dp)
                                .clickable {
                                    language.value = it.key
                                }
                                .bgRounded8Primary00NonStroke()
                                .wrapContentHeight()
                                .wrapContentWidth()
                                .padding(8.dp),
                            text = it.value,
                            color = if (it.key == language.value) {
                                MaterialTheme.NewsgramColors.designSystem.TertiaryText
                            } else {
                                MaterialTheme.NewsgramColors.designSystem.PrimaryText
                            },
                            style = MaterialTheme.NewsgramTypography.text14
                        )
                    }
                }
            }
        }
        item {
            LazyRow(
                modifier = Modifier
                    .padding(5.dp)
            ) {
                item {
                    Text(
                        modifier = Modifier
                            .padding(5.dp)
                            .wrapContentHeight()
                            .wrapContentWidth()
                            .padding(8.dp),
                        text = stringResource(id = R.string.sort_by),
                        color = MaterialTheme.NewsgramColors.designSystem.PrimaryText,
                        style = MaterialTheme.NewsgramTypography.text14
                    )
                }
                sortByList.value.forEach {
                    item {
                        Text(
                            modifier = Modifier
                                .padding(5.dp)
                                .clickable {
                                    sortBy.value = it.key
                                }
                                .bgRounded8Primary00NonStroke()
                                .wrapContentHeight()
                                .wrapContentWidth()
                                .padding(8.dp),
                            text = it.value,
                            color = if (it.key == sortBy.value) {
                                MaterialTheme.NewsgramColors.designSystem.TertiaryText
                            } else {
                                MaterialTheme.NewsgramColors.designSystem.PrimaryText
                            },
                            style = MaterialTheme.NewsgramTypography.text14
                        )
                    }
                }
            }
        }

        if (searchList.itemCount > 0) {

            item {
                Text(
                    modifier = Modifier
                        .padding(10.dp)
                        .wrapContentHeight()
                        .wrapContentWidth()
                        .padding(8.dp),
                    text = stringResource(id = R.string.news),
                    color = MaterialTheme.NewsgramColors.designSystem.Neutral30,
                    style = MaterialTheme.NewsgramTypography.text14
                )
            }
            items(searchList.itemCount) {
                NewsItem(article = searchList[it])
//            Text(text = searchList[it]?.title ?: "")
            }
        }
    }
}


@Composable
fun NewsItem(
    modifier: Modifier = Modifier,
    article: Article?
) {
    ConstraintLayout(
        modifier = modifier.padding(10.dp).fillMaxWidth()
    ) {
        val (imgCover, txtTitle, txtPublishDate, txtSource) = createRefs()

        Box(
            modifier = modifier
                .padding(vertical = 4.dp)
                .constrainAs(imgCover) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }
                .size(96.dp)
                .clip(
                    RoundedCornerShape(16.dp)
                ),
            contentAlignment = Alignment.BottomStart
        ) {
            AsyncImage(
                model = article?.urlToImage /*?: "https://cdn.abcotvs.com/dip/images/12910521_030323-wls-insect-img.jpg?w=1600"*/,
                requestBuilder = { crossfade(true) },
                contentDescription = "Cuisine image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        Text(
            modifier = Modifier
                .constrainAs(txtTitle) {
                    top.linkTo(parent.top)
                    start.linkTo(imgCover.end)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
                .padding(5.dp)
                .wrapContentHeight()
                .padding(8.dp),
            text = article?.title ?: "",
            color = MaterialTheme.NewsgramColors.designSystem.Neutral30,
            style = MaterialTheme.NewsgramTypography.text14Bold
        )


        Text(
            modifier = Modifier
                .constrainAs(txtPublishDate) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(imgCover.end)
                }
                .padding(5.dp)
                .wrapContentHeight()
                .wrapContentWidth()
                .padding(8.dp),
            text = article?.publishedAt?.toLocalTime()  ?: "",
            color = MaterialTheme.NewsgramColors.designSystem.Neutral30,
            style = MaterialTheme.NewsgramTypography.text10
        )


        Text(
            modifier = Modifier
                .constrainAs(txtSource) {
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                }
                .padding(5.dp)
                .wrapContentHeight()
                .wrapContentWidth()
                .padding(8.dp),
            text = article?.source?.name ?: "",
            color = MaterialTheme.NewsgramColors.designSystem.Neutral30,
            style = MaterialTheme.NewsgramTypography.text10
        )
    }
}