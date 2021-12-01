package eu.example.profilecardlayout.ui.theme

import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    //medium = RoundedCornerShape(4.dp), This is default from materialthemes when using a card
    medium = CutCornerShape(topEnd = 24.dp), // my own custom shape - topEnd = right / Use topStart for left
    large = RoundedCornerShape(0.dp)
)