package ua.vitolex.qrcodegenerator_qrcodescan.presentation.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import ua.vitolex.qrcodegenerator_qrcodescan.R
import ua.vitolex.qrcodegenerator_qrcodescan.presentation.navigation.Screens
import ua.vitolex.qrcodegenerator_qrcodescan.ui.theme.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                Modifier
                    .fillMaxWidth()
                    .height(75.dp),
                backgroundColor = Black333
            ) {
                Column(
                    Modifier
                        .padding(top = 15.dp, bottom = 0.dp, start = 5.dp)
                ) {
                    Text(text = "CODERATOR", color = WhiteGrey)
                    Spacer(modifier = Modifier.height(8.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(3.dp)
                            .background(Silver)
                    )
                    Row(
                        Modifier
                            .height(30.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            text = "QR code scanner and generator",
                            fontSize = 10.sp,
                            fontFamily = presstart,
                            color = WhiteGrey,
                        )

                    }
                }
            }
        },
        modifier = Modifier
            .fillMaxSize()
            .background(Black333)
            .padding(10.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Black333),
            contentAlignment = Alignment.Center,
        ) {
            Row(
                modifier = Modifier
                    .width(370.dp)
                    .background(Black333)
                    .border(3.dp, Silver, shape = RoundedCornerShape(14.dp))
                    .padding(8.dp)
                    .clip(shape = RoundedCornerShape(8.dp)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .background(PrimaryGray)
                        .width(175.dp)
                        .height(345.dp)
                        .clickable {
                            navController.navigate(Screens.ScanScreen.rout)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Spacer(modifier = Modifier.height(40.dp))
                        Image(
                            painter = painterResource(id = R.drawable.barcode_100),
                            contentDescription = "Qr_code",
                            modifier = Modifier
                                .scale(4f)
                        )
                        Spacer(modifier = Modifier.height(40.dp))
                        Text(
                            text = "Scanner",
                            fontSize = 12.sp,
                            modifier = Modifier.padding(20.dp),
                            textAlign = TextAlign.Center
                        )
                    }

                }

                Spacer(modifier = Modifier.width(5.dp))
                Box(
                    modifier = Modifier
                        .background(PrimaryGray)
                        .width(175.dp)
                        .height(345.dp)
                        .clickable {
                            navController.navigate(Screens.InputScreen.rout)
                        },
                    contentAlignment = Alignment.Center,
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Spacer(modifier = Modifier.height(40.dp))
                        Image(
                            painter = painterResource(id = R.drawable.qr_code_100),
                            contentDescription = "Qr_code",
                            modifier = Modifier
                                .scale(4f)
                        )
                        Spacer(modifier = Modifier.height(40.dp))
                        Text(
                            text = "Generator",
                            fontSize = 12.sp,
                            modifier = Modifier.padding(20.dp),
                            lineHeight = 16.sp
                        )
                    }

                }
            }
        }
    }
}