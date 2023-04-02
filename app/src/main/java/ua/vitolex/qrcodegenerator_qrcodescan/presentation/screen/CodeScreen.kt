package ua.vitolex.qrcodegenerator_qrcodescan.presentation.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ua.vitolex.qrcodegenerator_qrcodescan.R
import ua.vitolex.qrcodegenerator_qrcodescan.presentation.GeneratorViewModel
import ua.vitolex.qrcodegenerator_qrcodescan.presentation.components.BannerAdView
import ua.vitolex.qrcodegenerator_qrcodescan.ui.theme.*
import ua.vitolex.qrcodegenerator_qrcodescan.utils.SaveMediaUtils
import ua.vitolex.qrcodegenerator_qrcodescan.utils.ShareUtils

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CodeScreen(
    navController: NavHostController,
    viewModel: GeneratorViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
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
                            .height(32.dp)
                            .clickable {
                                navController.popBackStack()
                            },
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            Modifier
                                .height(20.dp)
                                .padding(bottom = 0.dp, top = 0.dp)
                                .background(androidx.compose.ui.graphics.Color.Transparent),
                            contentAlignment = Alignment.TopEnd
                        ) {
                            Canvas(modifier = Modifier
                                .width(40.dp),
                                onDraw = {
                                    val trianglePath = Path().apply {
                                        moveTo(0.dp.toPx(), 11.dp.toPx())
                                        lineTo(30.dp.toPx(), 7.dp.toPx())
                                        lineTo(30.dp.toPx(), 15.dp.toPx())
                                    }
                                    drawPath(
                                        color = DirtyRed,
                                        path = trianglePath
                                    )
                                })
                        }
                        Text(
                            text = "back",
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
        Column(
        ) {
            LazyColumn(
                Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .background(Black333),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                item {
                    Spacer(modifier = Modifier.height(24.dp))
                    Column(
                        modifier = Modifier
                            .padding(top = 16.dp, bottom = 12.dp, start = 12.dp, end = 12.dp,
                            ),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = "QR code",
                            style = MaterialTheme.typography.body1,
                            fontSize = 18.sp,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            color = WhiteGrey
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        if (viewModel.mapBitmap.value != null) {
                            Image(
                                bitmap = viewModel.mapBitmap.value!!.asImageBitmap(),
                                contentDescription = "Map snapshot",
                                modifier = Modifier
                                    .border(30.dp, Dark, shape = RoundedCornerShape(50.dp))
                                    .padding(vertical = 30.dp, horizontal = 30.dp)
                                    .border(2.dp, Silver, shape = RoundedCornerShape(22.dp))
                                    .padding(8.dp)
                                    .clip(shape = RoundedCornerShape(14.dp)),
                            )
                        }
                    }

                }
                item {
                    Spacer(modifier = Modifier.height(14.dp))
                    Text(
                        text = "Save or share QR code",
                        style = MaterialTheme.typography.body2,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(12.dp),
                        textAlign = TextAlign.Center,
                        color = WhiteGrey
                    )
                    Row(
                        modifier = Modifier
                            .width(180.dp)
                            .height(83.dp)
                            .border(3.dp, Silver, shape = RoundedCornerShape(10.dp))
                            .padding(vertical = 8.dp, horizontal = 8.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(
                            colors = ButtonDefaults.buttonColors(backgroundColor = PrimaryGray),
                            onClick = {
                                if (viewModel.mapBitmap.value != null) {
                                    SaveMediaUtils.saveMediaToStorage(
                                        bitmap = viewModel.mapBitmap.value!!,
                                        context = context
                                    )
                                }
                            },
                            modifier = Modifier
                                .width(80.dp)
                                .height(80.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.downloading_icon),
                                contentDescription = "Save",
                                modifier = Modifier
                                    .scale(1.5f)
                            )
                        }
                        Spacer(modifier = Modifier.width(4.dp))
                        Button(
                            colors = ButtonDefaults.buttonColors(backgroundColor = PrimaryGray),
                            onClick = {
                                if (viewModel.mapBitmap.value != null) {
                                    ShareUtils.shareImageToOthers(
                                        context = context,
                                        bitmap = viewModel.mapBitmap.value!!
                                    )
                                }
                            },
                            modifier = Modifier
                                .width(80.dp)
                                .height(80.dp)

                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.share_icon),
                                contentDescription = "Share",
                                modifier = Modifier
                                    .scale(1.4f)
                            )
                        }
                    }
                }
            }
            Box(modifier = Modifier.fillMaxWidth().height(60.dp).background(Black333),){
//                BannerAdView(id = stringResource(id = R.string.banner_ad_unit_id_code))
            }
        }

    }
}