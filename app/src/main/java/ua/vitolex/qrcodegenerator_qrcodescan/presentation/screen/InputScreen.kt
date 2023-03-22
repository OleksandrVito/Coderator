package ua.vitolex.qrcodegenerator_qrcodescan.presentation.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.bundleOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ua.vitolex.qrcodegenerator_qrcodescan.Constants.TypeContent.EMAIL
import ua.vitolex.qrcodegenerator_qrcodescan.Constants.TypeContent.LOCATION
import ua.vitolex.qrcodegenerator_qrcodescan.Constants.TypeContent.PHONE
import ua.vitolex.qrcodegenerator_qrcodescan.Constants.TypeContent.TEXT
import ua.vitolex.qrcodegenerator_qrcodescan.Constants.TypeContent.WEBSITE
import ua.vitolex.qrcodegenerator_qrcodescan.R
import ua.vitolex.qrcodegenerator_qrcodescan.presentation.GeneratorViewModel
import ua.vitolex.qrcodegenerator_qrcodescan.presentation.components.*
import ua.vitolex.qrcodegenerator_qrcodescan.presentation.navigation.Screens
import ua.vitolex.qrcodegenerator_qrcodescan.ui.theme.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun InputScreen(
    navController: NavController,
    viewModel: GeneratorViewModel = hiltViewModel(),
) {
    var type by remember { mutableStateOf("TEXT_TYPE") }
    var inputValue by remember { mutableStateOf("") }
    var inputSecondValue by remember { mutableStateOf("") }

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
            verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn(
                Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .background(Black333),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item { Spacer(modifier = Modifier.height(24.dp)) }
                item {
                    Text(
                        text = "text • email • phone • website • location",
                        style = MaterialTheme.typography.body2,
                        color = WhiteGrey,
                        textAlign = TextAlign.Center,
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(3.dp, Silver, shape = RoundedCornerShape(14.dp))
                                .padding(6.dp)
                                .clip(shape = RoundedCornerShape(12.dp))

                        ) {
                            Row(modifier = Modifier.fillMaxWidth()) {
                                ButtonToSelectType(
                                    type = type,
                                    typeButton = TEXT,
                                    title = "TEXT",
                                    changeType = {
                                        type = it
                                        inputValue = ""
                                        inputSecondValue = ""
                                    },
                                    modifier = Modifier
                                        .weight(1f),
                                    icon = painterResource(R.drawable.text_icon)
                                )
                                ButtonToSelectType(
                                    type = type,
                                    typeButton = EMAIL,
                                    title = "EMAIL",
                                    changeType = {
                                        type = it
                                        inputValue = ""
                                        inputSecondValue = ""
                                    },
                                    modifier = Modifier
                                        .weight(1f),
                                    icon = painterResource(R.drawable.email_icon)
                                )
                                ButtonToSelectType(
                                    type = type,
                                    typeButton = PHONE,
                                    title = "PHONE",
                                    changeType = {
                                        type = it
                                        inputValue = ""
                                        inputSecondValue = ""
                                    },
                                    modifier = Modifier
                                        .weight(1f),
                                    icon = painterResource(R.drawable.phone_icon)
                                )
                                ButtonToSelectType(
                                    type = type,
                                    typeButton = WEBSITE,
                                    title = "WEBSITE",
                                    changeType = {
                                        type = it
                                        inputValue = ""
                                        inputSecondValue = ""
                                    },
                                    modifier = Modifier
                                        .weight(1f),
                                    icon = painterResource(R.drawable.website_icon)
                                )
                                ButtonToSelectType(
                                    type = type,
                                    typeButton = LOCATION,
                                    title = "LOCATION",
                                    changeType = {
                                        type = it
                                        inputValue = ""
                                        inputSecondValue = ""
                                    },
                                    modifier = Modifier
                                        .weight(1f),
                                    icon = painterResource(id = R.drawable.place_marker)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(42.dp))
                        when (type) {
                            TEXT -> TextInput(
                                value = inputValue,
                                onValueChange = {
                                    if (it.length <= 150) {
                                        inputValue = it
                                    }
                                }, clearInput = { inputValue = "" },
                                placeholder = "Enter text"
                            )
                            EMAIL -> EmailPhoneInput(
                                value = inputValue,
                                onValueChange = {
                                    inputValue = it
                                },
                                keyboardType = KeyboardType.Email,
                                placeholder = "Enter email"
                            )
                            PHONE -> EmailPhoneInput(
                                value = inputValue,
                                onValueChange = {
                                    inputValue = it
                                },
                                keyboardType = KeyboardType.Phone,
                                placeholder = "Enter phone number"
                            )
                            LOCATION -> LocationInput(
                                value = inputValue,
                                onValueChange = {
                                    inputValue = it
                                },
                                secondValue = inputSecondValue,
                                onSecondValueChange = { inputSecondValue = it }
                            )

                            WEBSITE -> WebsiteInput(
                                value = inputValue,
                                onValueChange = {
                                    inputValue = it
                                },
                                clearInput = { inputValue = "" },
                                placeholder = "Enter URL address",
                                onPlusHint = {
                                    inputValue = inputValue + it
                                }
                            )
                        }
                        Spacer(modifier = Modifier.height(24.dp))
                        Button(
                            colors = ButtonDefaults.buttonColors(backgroundColor = PrimaryGray),
                            onClick = {
                                if (!inputValue.isBlank()) {
                                    when (type) {
                                        EMAIL ->
                                            viewModel.startGeneration(
                                                "${inputValue.trim()}?subject=&body=&",
                                                type
                                            )
                                        WEBSITE -> viewModel.startGeneration(
                                            if (inputValue.contains("https://") || inputValue.contains("http://")) {
                                                inputValue
                                            } else {
                                                "https://$inputValue"
                                            },
                                            "TEXT_TYPE"
                                        )
                                        LOCATION -> viewModel.startGeneration(
                                            inputValue = inputValue,
                                            type = type,
                                            bundle = bundleOf(
                                                Pair("LAT", inputValue.toFloatOrNull()),
                                                Pair("LONG", inputSecondValue.toFloatOrNull())
                                            ),
                                        )
                                        else -> viewModel.startGeneration(inputValue, type)
                                    }
                                    if (viewModel.mapBitmap.value != null) {
                                        navController.navigate(Screens.CodeScreen.rout)
                                    }
                                }

                            },
                            modifier = Modifier
                                .height(64.dp)
                                .border(3.dp, Silver, shape = RoundedCornerShape(10.dp))
                                .padding(vertical = 8.dp, horizontal = 8.dp)
                        ) {
                            Text(text = "GENERATE", fontFamily = presstart)
                        }
                    }
                }
            }
            BannerAdView(id = stringResource(id = R.string.banner_ad_unit_id_input))
        }

    }
}



