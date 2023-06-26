package ua.vitolex.qrcodegenerator_qrcodescan.presentation.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import ua.vitolex.qrcodegenerator_qrcodescan.R
import ua.vitolex.qrcodegenerator_qrcodescan.presentation.components.BannerAdView
import ua.vitolex.qrcodegenerator_qrcodescan.presentation.components.ResponsiveText
import ua.vitolex.qrcodegenerator_qrcodescan.presentation.navigation.Screens
import ua.vitolex.qrcodegenerator_qrcodescan.ui.theme.*
import ua.vitolex.qrcodegenerator_qrcodescan.utils.WindowInfo
import ua.vitolex.qrcodegenerator_qrcodescan.utils.rememberWindowInfo

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavHostController) {
    val windowInfo = rememberWindowInfo()

    Scaffold(
        topBar = {
            TopAppBar(
                Modifier
                    .fillMaxWidth()
                    .heightIn(min = 75.dp),
                backgroundColor = Black333
            ) {
                Column(
                    Modifier
                        .padding(top = 10.dp, bottom = 0.dp, start = 5.dp)
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
                            .heightIn(min=32.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "QR code scanner and generator",
                            fontSize = 10.sp,
                            fontFamily = presstart,
                            color = WhiteGrey,
                            maxLines= 1,
                            overflow = TextOverflow.Ellipsis
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
        Column() {
            if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact){
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                        .background(Black333),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
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
                                    .fillMaxWidth(0.5f)
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
                                        painter = painterResource(id = R.drawable.scanner_icon),
                                        contentDescription = "Qr_code",
                                        modifier = Modifier
                                            .scale(4f)
                                    )
                                    Spacer(modifier = Modifier.height(40.dp))
                                    ResponsiveText(
                                        modifier= Modifier.padding(10.dp),
                                        text= "Scanner",
                                        color= Black27,
                                        textStyle= MaterialTheme.typography.body1,
                                        targetTextSizeHeight= 12.sp,
                                    )
                                }

                            }

                            Spacer(modifier = Modifier.width(5.dp))
                            Box(
                                modifier = Modifier
                                    .background(PrimaryGray)
                                    .fillMaxWidth()
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
                                        painter = painterResource(id = R.drawable.qr_code_icon),
                                        contentDescription = "Qr_code",
                                        modifier = Modifier
                                            .scale(4f)
                                    )
                                    Spacer(modifier = Modifier.height(40.dp))
                                    ResponsiveText(
                                        modifier= Modifier.padding(10.dp),
                                        text= "Generator",
                                        color= Black27,
                                        textStyle= MaterialTheme.typography.body1,
                                        targetTextSizeHeight= 12.sp,
                                    )
                                }

                            }
                        }
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                        .background(Black333),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(0.8f)
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
                                    .fillMaxWidth(0.5f)
                                    .heightIn(min=180.dp)
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
                                        painter = painterResource(id = R.drawable.scanner_icon),
                                        contentDescription = "Qr_code",
                                        modifier = Modifier
                                            .scale(4f)
                                    )
                                    Spacer(modifier = Modifier.height(40.dp))
                                    ResponsiveText(
                                        modifier= Modifier.padding(10.dp),
                                        text= "Scanner",
                                        color= Black27,
                                        textStyle= MaterialTheme.typography.body1,
                                        targetTextSizeHeight= 12.sp,
                                    )
                                }

                            }

                            Spacer(modifier = Modifier.width(5.dp))
                            Box(
                                modifier = Modifier
                                    .background(PrimaryGray)
                                    .fillMaxWidth()
                                    .heightIn(min=180.dp)
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
                                        painter = painterResource(id = R.drawable.qr_code_icon),
                                        contentDescription = "Qr_code",
                                        modifier = Modifier
                                            .scale(4f)
                                    )
                                    Spacer(modifier = Modifier.height(40.dp))
                                    ResponsiveText(
                                        modifier= Modifier.padding(10.dp),
                                        text= "Generator",
                                        color= Black27,
                                        textStyle= MaterialTheme.typography.body1,
                                        targetTextSizeHeight= 12.sp,
                                    )
                                }

                            }
                        }
                    }
                }
            }
            Box(modifier = Modifier.fillMaxWidth().height(60.dp).background(Black333),){
                BannerAdView(id = stringResource(id = R.string.banner_ad_unit_id_main))
            }
        }
    }
}