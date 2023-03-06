package ir.reza_mahmoudi.newsgram.core.presentation.compose_components.navigation

sealed class Screen(val route: String) {
    object SearchNews : Screen("search_news_screen")
    object TopHeadline : Screen("top_headline_news_screen")
}