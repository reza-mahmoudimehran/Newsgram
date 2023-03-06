package ir.reza_mahmoudi.newsgram.core.presentation.design_system.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.ui.graphics.Color

data class AppColors(
    val material: ColorScheme,
    val designSystem: DesignSystem = DesignSystem(),
)

data class DesignSystem(
    val Primary: Color = Blue20,
    val Primary00: Color = Blue00,
    val Primary10: Color = Blue10,
    val Primary20: Color = Blue20,
    val Primary30: Color = Blue30,
    val Primary40: Color = Blue40,

    val Secondary: Color = Green10,

    val Neutral00: Color = Gray00,
    val Neutral10: Color = Gray10,
    val Neutral20: Color = Gray20,
    val Neutral30: Color = Gray30,
    val Neutral40: Color = Gray40,
    val Neutral50: Color = Gray50,

    val PrimaryBackground: Color = White,

    val PrimaryText: Color = Gray50,
    val SecondaryText: Color = Lemon,
    val TertiaryText: Color = White,
    val PrimaryLink: Color = Green10,
    val DisabledText: Color = Gray20,


    val BottomSheetBackground: Color = Blue40,
    val BottomSheetDefaultIcon: Color = Gray10,
    val BottomSheetSelectedIcon: Color = Green00,
)

