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
import ir.reza_mahmoudi.newsgram.core.presentation.design_system.shapes.bgRounded8Neutral00StrokeNeutral10
import ir.reza_mahmoudi.newsgram.core.presentation.design_system.shapes.bgRounded8Neutral00StrokePrimary00
import ir.reza_mahmoudi.newsgram.core.presentation.design_system.theme.NewsgramColors
import ir.reza_mahmoudi.newsgram.core.presentation.design_system.theme.NewsgramTypography

@Composable
fun SearchNewsScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchNewsViewModel = hiltViewModel()
) {
    val query: MutableState<String?> = remember { mutableStateOf(null) }
    val language: MutableState<String?> = remember { mutableStateOf("en") }
    val sortBy: MutableState<String?> = remember { mutableStateOf("publishedAt") }

    val languageList = remember {
        mutableStateOf(
            listOf(
                "ar",
                "de",
                "en",
                "es",
                "fr",
                "he",
                "it",
                "nl",
                "no",
                "pt",
                "ru",
                "sv",
                "ud",
                "zh"
            )
        )
    }
    val sortByList = remember { mutableStateOf(listOf("publishedAt", "relevancy", "popularity")) }
//    val search = remember {  mutableStateOf("text") }

    val searchList =
        viewModel.searchNews(query = query.value, language = language.value, sortBy = sortBy.value)
            .collectAsLazyPagingItems()

    LazyColumn(modifier = modifier) {
        item {
            SearchBox(text = query)
        }
        item {
            LazyRow {
                languageList.value.forEach {
                    item {
                        Text(
                            modifier = Modifier
                                .padding(5.dp)
                                .clickable {
                                    language.value = it
                                },
                            text = it
                        )
                    }
                }
            }
        }
        item {
            LazyRow {
                sortByList.value.forEach {
                    item {
                        Text(
                            modifier = Modifier
                                .padding(5.dp)
                                .clickable {
                                    sortBy.value = it
                                },
                            text = it
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
            .bgRounded8Neutral00StrokePrimary00()
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
            textStyle = MaterialTheme.NewsgramTypography.text12.copy(color = MaterialTheme.NewsgramColors.designSystem.Neutral40),
            decorationBox = { innerTextField ->
                Row(modifier = Modifier.fillMaxWidth()) {
                    if (value.isEmpty()) {
                        Text(
                            text = stringResource(id = R.string.app_name),
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