package ir.reza_mahmoudi.newsgram.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import ir.reza_mahmoudi.newsgram.core.presentation.design_system.theme.NewsgramTheme
import ir.reza_mahmoudi.newsgram.core.presentation.main_screen.MainScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsgramTheme(true) {
                MainScreen()
            }
        }
    }
}
