package ir.reza_mahmoudi.newsgram.core.presentation.main_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import ir.reza_mahmoudi.newsgram.core.presentation.compose_components.navigation.BottomNavigationBar
import ir.reza_mahmoudi.newsgram.core.presentation.compose_components.navigation.Navigation
import ir.reza_mahmoudi.newsgram.core.presentation.design_system.theme.NewsgramColors


@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) },
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                Navigation(navController = navController)
            }
        },
        backgroundColor = MaterialTheme.NewsgramColors.designSystem.PrimaryBackground
    )
}