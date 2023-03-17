package ua.vitolex.qrcodegenerator_qrcodescan.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ua.vitolex.qrcodegenerator_qrcodescan.ui.theme.PrimaryGray
import ua.vitolex.qrcodegenerator_qrcodescan.ui.theme.SecondaryGray

@Composable
fun ButtonToSelectType(
    type: String,
    typeButton: String,
    title: String,
    changeType: (String)->Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier
        .fillMaxWidth()
        .height(30.dp)
        .clickable {
            changeType(typeButton)
        }
        .background(
            if (type == typeButton) SecondaryGray else PrimaryGray
        )
        .border(1.dp,Color(0xFF030406)),
        contentAlignment = Alignment.Center) {
        Text(text = title, fontSize = 8.sp, color = Color(0xFF030406))
    }
}