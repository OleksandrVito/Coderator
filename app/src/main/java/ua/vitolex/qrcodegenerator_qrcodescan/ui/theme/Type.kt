package ua.vitolex.qrcodegenerator_qrcodescan.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ua.vitolex.qrcodegenerator_qrcodescan.R


val presstart = FontFamily(
    Font(
        R.font.presstart,
        weight = FontWeight.Normal
    ),
)

val rabbit = FontFamily(
    Font(
        R.font.white_rabbit,
        weight = FontWeight.Normal
    )
)


// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = presstart,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = Black27
    ),
    body2 = TextStyle(
        fontFamily = rabbit,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = Black27
    ),
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)