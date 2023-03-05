package ir.reza_mahmoudi.newsgram.core.presentation.compose_components.navigation

import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import ir.reza_mahmoudi.newsgram.R
import ir.reza_mahmoudi.newsgram.core.presentation.design_system.theme.NewsgramColors

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        NavigationItem.TopHeadline,
        NavigationItem.SearchNews,
    )
    BottomNavigation(
        modifier= Modifier.wrapContentHeight(),
        backgroundColor = MaterialTheme.NewsgramColors.designSystem.Primary40,
        contentColor =MaterialTheme.NewsgramColors.designSystem.BottomSheetDefaultIcon
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = stringResource(id = item.title)) },
                selectedContentColor = MaterialTheme.NewsgramColors.designSystem.BottomSheetSelectedIcon,
                unselectedContentColor = MaterialTheme.NewsgramColors.designSystem.BottomSheetDefaultIcon,
                alwaysShowLabel = true,
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                }
            )
        }
    }
}

sealed class NavigationItem(var screen: Screen, var icon: Int, var title: Int) {
    object TopHeadline : NavigationItem(
        Screen.TopHeadline,
        R.drawable.ic_article,
        R.string.top_headlines
    )
    object SearchNews : NavigationItem(
        Screen.SearchNews,
        R.drawable.ic_search,
        R.string.search
    )
}









