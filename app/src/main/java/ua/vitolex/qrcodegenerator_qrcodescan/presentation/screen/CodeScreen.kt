package ua.vitolex.qrcodegenerator_qrcodescan.presentation.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import ua.vitolex.qrcodegenerator_qrcodescan.R
import ua.vitolex.qrcodegenerator_qrcodescan.presentation.GeneratorViewModel
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
                            .height(30.dp)
                            .clickable {
                                navController.popBackStack()
                            },
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            Modifier
                                .height(30.dp)
                                .padding(bottom = 15.dp, top = 0.dp)
                                .background(Color.Transparent)
                        ) {
                            Canvas(modifier = Modifier
                                .width(50.dp)
                                .height(30.dp), onDraw = {
                                val trianglePath = Path().apply {
                                    moveTo(2.dp.toPx(), 15.dp.toPx())
                                    lineTo(30.dp.toPx(), 11.dp.toPx())
                                    lineTo(30.dp.toPx(), 19.dp.toPx())
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
            Modifier
                .fillMaxSize()
                .background(Black333),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = "QR Code", fontFamily = presstart, color = WhiteGrey)
            if (viewModel.mapBitmap.value != null) {
                Image(
                    bitmap = viewModel.mapBitmap.value!!.asImageBitmap(),
                    contentDescription = "Map snapshot",
                    modifier = Modifier
                        .border(2.dp, Silver, shape = RoundedCornerShape(50.dp))
                        .padding(top = 35.dp, bottom = 20.dp, start = 15.dp, end = 15.dp)
                        .border(22.dp, Dark, shape = RoundedCornerShape(60.dp))
                        .padding(vertical = 30.dp, horizontal = 30.dp)
                        .border(2.dp, Silver, shape = RoundedCornerShape(30.dp))
                        .padding(8.dp)
                        .clip(shape = RoundedCornerShape(22.dp)),
                )
            }
            Text(
                text = "Save or share QR code",
                fontSize = 12.sp,
                modifier = Modifier.padding(20.dp),
                lineHeight = 16.sp,
                textAlign = TextAlign.Center,
                color = WhiteGrey
            )
            Row(
                modifier = Modifier
                    .width(248.dp)
                    .height(103.dp)
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
                        .width(120.dp)
                        .height(100.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.downloading_100),
                        contentDescription = "Save",
                        modifier = Modifier
                            .scale(2f)
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
                        .width(120.dp)
                        .height(100.dp)

                ) {
                    Image(
                        painter = painterResource(id = R.drawable.share_100),
                        contentDescription = "Share",
                        modifier = Modifier
                            .scale(2f)
                    )
                }
            }

        }

    }

}