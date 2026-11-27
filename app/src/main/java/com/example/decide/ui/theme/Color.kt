package com.example.decide.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

enum class AppTheme {
    LIGHT,
    DARK,
    CREAM,
    NAVY,
}

private val Navy950 = Color(0xFF081A2A)
private val Navy900 = Color(0xFF0F2B44)
private val Navy800 = Color(0xFF17466B)
private val Navy200 = Color(0xFFB4D0E8)
private val Navy100 = Color(0xFFD0E4F7)

private val Cream50 = Color(0xFFFFF8F0)
private val Cream100 = Color(0xFFF3E7D7)
private val Cream200 = Color(0xFFE8D8C4)
private val Cream900 = Color(0xFF2B2118)

private val Gold700 = Color(0xFF7A5C31)
private val Gold200 = Color(0xFFFFDDB1)
private val White = Color(0xFFFFFFFF)

val LightColorScheme = lightColorScheme(
    primary = Color(0xFF415F91),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFD6E3FF),
    onPrimaryContainer = Color(0xFF001B3E),
    secondary = Color(0xFF565F71),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFD9E3F8),
    onSecondaryContainer = Color(0xFF131C2B),
    tertiary = Color(0xFF705575),
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFFFAD8FD),
    onTertiaryContainer = Color(0xFF29132E),
    error = Color(0xFFBA1A1A),
    onError = Color(0xFFFFFFFF),
    errorContainer = Color(0xFFFFDAD6),
    onErrorContainer = Color(0xFF410002),
    background = Color(0xFFF9F9FF),
    onBackground = Color(0xFF191C20),
    surface = Color(0xFFF9F9FF),
    onSurface = Color(0xFF191C20),
    surfaceVariant = Color(0xFFE0E2EC),
    onSurfaceVariant = Color(0xFF44474F),
    outline = Color(0xFF74777F),
)

val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFA9C7FF),
    onPrimary = Color(0xFF0A305F),
    primaryContainer = Color(0xFF284777),
    onPrimaryContainer = Color(0xFFD6E3FF),
    secondary = Color(0xFFBDC7DC),
    onSecondary = Color(0xFF283141),
    secondaryContainer = Color(0xFF3E4759),
    onSecondaryContainer = Color(0xFFD9E3F8),
    tertiary = Color(0xFFDDBCE0),
    onTertiary = Color(0xFF402843),
    tertiaryContainer = Color(0xFF573E5B),
    onTertiaryContainer = Color(0xFFFAD8FD),
    error = Color(0xFFFFB4AB),
    onError = Color(0xFF690005),
    errorContainer = Color(0xFF93000A),
    onErrorContainer = Color(0xFFFFDAD6),
    background = Color(0xFF111318),
    onBackground = Color(0xFFE1E2E8),
    surface = Color(0xFF111318),
    onSurface = Color(0xFFE1E2E8),
    surfaceVariant = Color(0xFF43474E),
    onSurfaceVariant = Color(0xFFC3C7CF),
    outline = Color(0xFF8D9199),
)

val CreamColorScheme = lightColorScheme(
    primary = Navy900,
    onPrimary = White,
    primaryContainer = Navy100,
    onPrimaryContainer = Navy950,
    secondary = Navy800,
    onSecondary = White,
    secondaryContainer = Cream200,
    onSecondaryContainer = Cream900,
    tertiary = Gold700,
    onTertiary = White,
    tertiaryContainer = Gold200,
    onTertiaryContainer = Cream900,
    error = Color(0xFFBA1A1A),
    onError = White,
    errorContainer = Color(0xFFFFDAD6),
    onErrorContainer = Color(0xFF410002),
    background = Cream50,
    onBackground = Cream900,
    surface = Cream50,
    onSurface = Cream900,
    surfaceVariant = Cream100,
    onSurfaceVariant = Color(0xFF4B463F),
    outline = Color(0xFF7C766D),
)

val NavyColorScheme = darkColorScheme(
    primary = Navy200,
    onPrimary = Navy950,
    primaryContainer = Navy800,
    onPrimaryContainer = Navy100,
    secondary = Cream200,
    onSecondary = Cream900,
    secondaryContainer = Navy900,
    onSecondaryContainer = Cream100,
    tertiary = Gold200,
    onTertiary = Cream900,
    tertiaryContainer = Gold700,
    onTertiaryContainer = Gold200,
    error = Color(0xFFFFB4AB),
    onError = Color(0xFF690005),
    errorContainer = Color(0xFF93000A),
    onErrorContainer = Color(0xFFFFDAD6),
    background = Navy950,
    onBackground = Cream100,
    surface = Navy950,
    onSurface = Cream100,
    surfaceVariant = Navy900,
    onSurfaceVariant = Cream200,
    outline = Navy200,
)
