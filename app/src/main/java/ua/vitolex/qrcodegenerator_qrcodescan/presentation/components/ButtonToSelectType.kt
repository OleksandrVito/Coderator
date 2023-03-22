package ua.vitolex.qrcodegenerator_qrcodescan.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ua.vitolex.qrcodegenerator_qrcodescan.ui.theme.Black333
import ua.vitolex.qrcodegenerator_qrcodescan.ui.theme.PrimaryGray
import ua.vitolex.qrcodegenerator_qrcodescan.ui.theme.SecondaryGray

@Composable
fun ButtonToSelectType(
    type: String,
    typeButton: String,
    title: String,
    changeType: (String) -> Unit,
    modifier: Modifier = Modifier,
    icon: Painter,
) {
    Box(modifier = modifier
        .fillMaxWidth()
        .height(50.dp)
        .clickable {
            changeType(typeButton)
        }
        .background(
            if (type == typeButton) SecondaryGray else PrimaryGray
        )
        .border(1.dp, Black333),
        contentAlignment = Alignment.Center) {
        Image(painter = icon, contentDescription = null)
    }
}