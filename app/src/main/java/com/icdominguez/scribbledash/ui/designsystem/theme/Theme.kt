package com.icdominguez.scribbledash.ui.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = primary,
    onPrimary = onPrimary,
    onPrimaryContainer = onPrimaryOpacity40,
    secondary = secondary,
    tertiaryContainer = tertiaryContainer,
    error = error,
)

private val typography = Typography()

@Composable
fun ScribbleDashTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = typography,
        content = content
    )
}