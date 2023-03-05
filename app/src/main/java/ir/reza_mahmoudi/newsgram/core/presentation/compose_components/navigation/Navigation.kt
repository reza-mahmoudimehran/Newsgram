package ir.reza_mahmoudi.newsgram.core.presentation.compose_components.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ir.reza_mahmoudi.newsgram.feature_search_news.presentation.SearchNewsScreen
import ir.reza_mahmoudi.newsgram.feature_top_headlines.presentation.TopHeadlineNewsScreen


@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController, startDestination = NavigationItem.TopHeadline.screen.route) {
        composable(NavigationItem.SearchNews.screen.route) {
            SearchNewsScreen()
        }
        composable(NavigationItem.TopHeadline.screen.route) {
            TopHeadlineNewsScreen()
        }
    }
}