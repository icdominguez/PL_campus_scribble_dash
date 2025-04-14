package com.icdominguez.scribbledash.ui.designsystem.theme

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

// region Brand
val primary = Color(0xFF238CFF)
val onPrimary = Color(0xFFFFFFFF)
val onPrimaryOpacity40 = Color(0x66FFFFFF)
val secondary = Color(0xFFAB5CFA)
val tertiaryContainer = Color(0xFFFA852C)
val error = Color(0xFFEF1242)
val success = Color(0XFF0DD280)
// endregion

// region Scheme
val background = Color(0xFFFEFAF6)
val backgroundGradientA = Color(0xFFFEFAF6)
val backgroundGradientB = Color(0xFFFFF1E2)
val onBackground = Color(0xFF514437)
val onBackgroundVariant = Color(0xFF7F7163)
val surfaceHigh = Color(0xFFFFFFFF)
val surfaceOpacity80 = Color(0x50FFFFFF)
val onSurface = Color(0xFFA5978A)
val surfaceLow = Color(0xFFEEE7E0)
val surfaceLowest = Color(0xFFE1D5CA)
val onSurfaceVariant = Color(0xFFF6F1EC)
// endregion

sealed class ScribbleDashColorPalette(
    val primary: Color,
    val onPrimary: Color,
    val onPrimaryOpacity40: Color,
    val secondary: Color,
    val tertiaryContainer: Color,
    val error: Color,
    val success: Color,
    val background: Color,
    val backgroundGradientA: Color,
    val backgroundGradientB: Color,
    val onBackground: Color,
    val onBackgroundVariant: Color,
    val surfaceHigh: Color,
    val surfaceOpacity80: Color,
    val onSurface: Color,
    val surfaceLow: Color,
    val surfaceLowest: Color,
    val onSurfaceVariant: Color,
)

@Stable
internal data object MainColorPalette : ScribbleDashColorPalette(
    primary = primary,
    onPrimary = onPrimary,
    onPrimaryOpacity40 = onPrimaryOpacity40,
    secondary = secondary,
    tertiaryContainer = tertiaryContainer,
    error = error,
    success = success,
    background = background,
    backgroundGradientA = backgroundGradientA,
    backgroundGradientB = backgroundGradientB,
    onBackground = onBackground,
    onBackgroundVariant = onBackgroundVariant,
    surfaceHigh = surfaceHigh,
    surfaceOpacity80 = surfaceOpacity80,
    onSurface = onSurface,
    surfaceLow = surfaceLow,
    surfaceLowest = surfaceLowest,
    onSurfaceVariant = onSurfaceVariant,
)

val LocalScribbleDashColorsPalette: ProvidableCompositionLocal<ScribbleDashColorPalette> =
    staticCompositionLocalOf { MainColorPalette }