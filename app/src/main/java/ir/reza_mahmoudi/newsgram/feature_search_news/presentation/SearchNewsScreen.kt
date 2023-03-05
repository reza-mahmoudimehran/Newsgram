package ir.reza_mahmoudi.newsgram.feature_search_news.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import ir.reza_mahmoudi.newsgram.R
import ir.reza_mahmoudi.newsgram.core.presentation.design_system.shapes.*
import ir.reza_mahmoudi.newsgram.core.presentation.design_system.theme.NewsgramColors
import ir.reza_mahmoudi.newsgram.core.presentation.design_system.theme.NewsgramTypography
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

    LazyColumn(modifier = modifier) {
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
                        color = MaterialTheme.NewsgramColors.designSystem.Neutral50,
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
                                MaterialTheme.NewsgramColors.designSystem.Neutral20
                            } else {
                                MaterialTheme.NewsgramColors.designSystem.Neutral50
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
                        color = MaterialTheme.NewsgramColors.designSystem.Neutral50,
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
                                MaterialTheme.NewsgramColors.designSystem.Neutral20
                            } else {
                                MaterialTheme.NewsgramColors.designSystem.Neutral50
                            },
                            style = MaterialTheme.NewsgramTypography.text14
                        )
                    }
                }
            }
        }
        items(searchList.itemCount) {
            Text(text = searchList[it]?.title ?: "")
        }
    }
}


@Composable
fun SearchBox(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    onFocusChanged: ((focusState: FocusState) -> Unit)? = null,
    text: MutableState<String?> = mutableStateOf("")
) {

    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    val focusRequester = remember { FocusRequester() }

    val focusModifier = if (isFocused) {
        modifier
            .padding(10.dp)
            .bgRounded8Neutral00StrokePrimary20()
    } else {
        modifier
            .padding(10.dp)
            .bgRounded8Neutral00StrokeNeutral10()
    }
    Row(
        modifier = focusModifier
            .fillMaxWidth()
            .wrapContentHeight()
            .height(42.dp)
            .focusRequester(focusRequester)
            .onFocusChanged {
//                isFocused=it.isFocused

                onFocusChanged?.let { focusState ->
                    focusState(it)
                }
            }
            .clickable {
                onClick?.let { it() }
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier
                .padding(10.dp)
                .width(20.dp)
                .height(20.dp),
            painter = painterResource(id = android.R.drawable.ic_menu_search),
            tint = MaterialTheme.NewsgramColors.designSystem.Neutral40,
            contentDescription = "ic_search",
        )

        var value by rememberSaveable { mutableStateOf("") }

        BasicTextField(
            cursorBrush = SolidColor(Color.Black),
            value = value,
            onValueChange = {
                value = it
                if (it.length > 2)
                    text.value = it
            },
            interactionSource = interactionSource,
            textStyle = MaterialTheme.NewsgramTypography.text14.copy(color = MaterialTheme.NewsgramColors.designSystem.Neutral40),
            decorationBox = { innerTextField ->
                Row(modifier = Modifier.fillMaxWidth()) {
                    if (value.isEmpty()) {
                        Text(
                            text = stringResource(id = R.string.search_text),
                            color = MaterialTheme.NewsgramColors.designSystem.Neutral20,
                            style = MaterialTheme.NewsgramTypography.text12,
                        )
                    }
                }
                innerTextField()
            },
            singleLine = true,
        )
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}