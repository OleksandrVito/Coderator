package ua.vitolex.qrcodegenerator_qrcodescan.presentation.screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ua.vitolex.qrcodegenerator_qrcodescan.Constants.TypeContent.CONTACT
import ua.vitolex.qrcodegenerator_qrcodescan.Constants.TypeContent.EMAIL
import ua.vitolex.qrcodegenerator_qrcodescan.Constants.TypeContent.LOCATION
import ua.vitolex.qrcodegenerator_qrcodescan.Constants.TypeContent.PHONE
import ua.vitolex.qrcodegenerator_qrcodescan.Constants.TypeContent.SMS
import ua.vitolex.qrcodegenerator_qrcodescan.Constants.TypeContent.TEXT
import ua.vitolex.qrcodegenerator_qrcodescan.presentation.GeneratorViewModel
import ua.vitolex.qrcodegenerator_qrcodescan.presentation.components.*
import ua.vitolex.qrcodegenerator_qrcodescan.presentation.navigation.Screens
import ua.vitolex.qrcodegenerator_qrcodescan.ui.theme.*
import ua.vitolex.qrcodegenerator_qrcodescan.utils.SaveMediaUtils.Companion.saveMediaToStorage
import ua.vitolex.qrcodegenerator_qrcodescan.utils.ShareUtils.Companion.shareImageToOthers


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
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(3.dp, Silver, shape = RoundedCornerShape(15.dp))
                        .padding(5.dp)
                        .clip(shape = RoundedCornerShape(13.dp))

                ) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        ButtonToSelectType(
                            type = type,
                            typeButton = TEXT,
                            title = "TEXT",
                            changeType = {
                                type = it
                            },
                            modifier = Modifier
                                .weight(1f)
                        )
                        ButtonToSelectType(
                            type = type,
                            typeButton = EMAIL,
                            title = "EMAIL",
                            changeType = {
                                type = it
                            },
                            modifier = Modifier
                                .weight(1f)
                        )
                        ButtonToSelectType(
                            type = type,
                            typeButton = PHONE,
                            title = "PHONE",
                            changeType = {
                                type = it
                            },
                            modifier = Modifier
                                .weight(1f)
                        )
                    }
                    Row(modifier = Modifier.fillMaxWidth()) {
                        ButtonToSelectType(
                            type = type,
                            typeButton = SMS,
                            title = "SMS",
                            changeType = {
                                type = it
                            },
                            modifier = Modifier
                                .weight(1f)
                        )
                        ButtonToSelectType(
                            type = type,
                            typeButton = CONTACT,
                            title = "CONTACT",
                            changeType = {
                                type = it
                            },
                            modifier = Modifier
                                .weight(1f)
                        )
                        ButtonToSelectType(
                            type = type,
                            typeButton = LOCATION,
                            title = "LOCATION",
                            changeType = {
                                type = it
                            },
                            modifier = Modifier
                                .weight(1f)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(50.dp))
                when (type) {
                    TEXT -> TextInput(
                        value = inputValue,
                        onValueChange = {
                            if (it.length <= 150) {
                                inputValue = it
                            }
                        }, clearInput = { inputValue = "" })
                    EMAIL -> EmailInput(
                        value = inputValue,
                        onValueChange = {
                            if (it.length <= 150) {
                                inputValue = it
                            }
                        },
                        clearInput = { inputValue = "" },
                        valueEmail = inputSecondValue,
                        onValueEmailChange = { inputSecondValue = it }
                    )
                    PHONE -> PhoneInput(
                        value = inputValue,
                        onValueChange = {
                            inputValue = it
                        })
                    SMS -> SmsInput(
                        value = inputValue,
                        onValueChange = {
                            if (it.length <= 150) {
                                inputValue = it
                            }
                        },
                        clearInput = { inputValue = "" },
                        valuePhone = inputSecondValue,
                        onValuePhoneChange = { inputSecondValue = it }
                    )

                    else -> TextInput(
                        value = inputValue,
                        onValueChange = { inputValue = it },
                        clearInput = { inputValue = "" })
                }
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    colors = ButtonDefaults.buttonColors(backgroundColor = PrimaryGray),
                    onClick = {
                        viewModel.startGeneration(inputValue, type)
                        if (viewModel.mapBitmap.value != null) {
                            navController.navigate(Screens.CodeScreen.rout)
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
}


