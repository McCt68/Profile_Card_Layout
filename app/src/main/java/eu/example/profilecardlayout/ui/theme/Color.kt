package eu.example.profilecardlayout.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

// My Custom Colors
val veryLightGrey = Color(0x60DCDCDC) // Color int
val lightGreen200 = Color(0x9932CD32) // Color int

// Just for testing
val test = Color(0xFF00BCD4) // Color int
val testLightGreen = Color(0xFF81C784) // Color LONG
val coloredSquare= Color(0xFF64B5F6) // Providing HEX

// extension function for Colors
// Colors.lightGreen
// This seems depricated ??
val Colors.lightGreen: Color
    @Composable
    get() = lightGreen200
