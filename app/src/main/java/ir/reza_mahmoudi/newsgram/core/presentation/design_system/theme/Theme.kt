package ir.reza_mahmoudi.newsgram.core.presentation.design_system.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.*

private val LightColorPalette = NewsgramColors(
    material = lightColorScheme(
        // You can override default colors
    ),
)

private val DarkColorPalette = NewsgramColors(
    material = darkColorScheme(
        // You can override default colors
    )
)

private val MainTypography = NewsgramTypography(
    material = Typography
)

@Composable
fun NewsgramTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }
    CompositionLocalProvider(
        LocalColors provides colors
    ) {
        MaterialTheme(
            colorScheme = colors.material,
            typography = MainTypography.material,
            content = content,
        )
    }
}

private val LocalColors = staticCompositionLocalOf { LightColorPalette }

val MaterialTheme.NewsgramColors: NewsgramColors
    @Composable
    @ReadOnlyComposable
    get() = LocalColors.current

val MaterialTheme.NewsgramTypography: NewsgramTypography
    @Composable
    @ReadOnlyComposable
    get() = MainTypography