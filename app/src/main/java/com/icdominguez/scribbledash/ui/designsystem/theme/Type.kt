package com.icdominguez.scribbledash.ui.designsystem.theme

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.icdominguez.scribbledash.R

private val DefaultFontFamily = FontFamily(
    Font(R.font.bagel_fat_one)
)

private val DefaultCustomStyle = TextStyle(
    fontFamily = DefaultFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 20.sp,
    lineHeight = 24.sp,
)

sealed class ScribbleDashTypography(
    val displayLarge: TextStyle,
    val displayMedium: TextStyle,
    val headlineLarge: TextStyle,
    val headlineMedium: TextStyle,
    val headlineSmall: TextStyle,
    val headlineXSmall: TextStyle,
    val bodyLarge: TextStyle,
    val bodyMedium: TextStyle,
    val bodySmall: TextStyle,
    val labelXLarge: TextStyle,
    val labelLarge: TextStyle,
    val labelMedium: TextStyle,
    val labelSmall: TextStyle
)

@Stable
internal data object MainTypography : ScribbleDashTypography(
    displayLarge = DefaultCustomStyle.copy(
        fontSize = 66.sp,
        lineHeight = 80.sp
    ),
    displayMedium = DefaultCustomStyle.copy(
        fontSize = 40.sp,
        lineHeight = 44.sp
    ),
    headlineLarge = DefaultCustomStyle.copy(
        fontSize = 34.sp,
        lineHeight = 48.sp
    ),
    headlineMedium = DefaultCustomStyle.copy(
        fontSize = 26.sp,
        lineHeight = 30.sp
    ),
    headlineSmall = DefaultCustomStyle.copy(
        fontSize = 18.sp,
        lineHeight = 26.sp
    ),
    headlineXSmall = DefaultCustomStyle.copy(
        fontSize = 14.sp,
        lineHeight = 18.sp
    ),
    bodyLarge = DefaultCustomStyle.copy(
        fontWeight = FontWeight.Medium
    ),
    bodyMedium = DefaultCustomStyle.copy(
        fontSize = 16.sp,
    ),
    bodySmall = DefaultCustomStyle.copy(
        fontSize = 14.sp,
        lineHeight = 18.sp
    ),
    labelXLarge = DefaultCustomStyle.copy(
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        lineHeight = 28.sp,
    ),
    labelLarge = DefaultCustomStyle.copy(
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp
    ),
    labelMedium = DefaultCustomStyle.copy(
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
    labelSmall = DefaultCustomStyle.copy(
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = 18.sp,
    )
)

val LocalScribbleDashTypography: ProvidableCompositionLocal<ScribbleDashTypography> =
    staticCompositionLocalOf {
        MainTypography
    }