package ua.vitolex.qrcodegenerator_qrcodescan.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ua.vitolex.qrcodegenerator_qrcodescan.R
import ua.vitolex.qrcodegenerator_qrcodescan.ui.theme.*

@Composable
fun TextInput(
    value: String,
    onValueChange: (String) -> Unit,
    clearInput: () -> Unit,
    placeholder: String,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(3.dp, Silver, shape = RoundedCornerShape(24.dp))
            .padding(16.dp)
            .clip(shape = RoundedCornerShape(8.dp))
            .background(Black333),
    ) {
        Text(
            text = "Input field",
            style = MaterialTheme.typography.body2,
            color = WhiteGrey,
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(10.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(shape = RoundedCornerShape(8.dp))
                .background(Black333),
            contentAlignment = Alignment.BottomEnd
        ) {
            OutlinedTextField(
                value = value,
                onValueChange = onValueChange,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = Color.Transparent,
                    disabledBorderColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent,
                    backgroundColor = PrimaryGray,
                    textColor = Black27,
                    cursorColor = Black27,
                    trailingIconColor = Black27
                ),
                modifier = Modifier
                    .fillMaxSize()
                    .border(2.dp, Silver, shape = RoundedCornerShape(16.dp))
                    .padding(8.dp)
                    .padding(bottom = 20.dp)
                    .clip(shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)),

                textStyle = MaterialTheme.typography.body2.copy(
                    lineHeight = 18.sp,
                    baselineShift = BaselineShift(-0.07f)
                ),
                placeholder = {
                    Text(
                        text = placeholder,
                        style = MaterialTheme.typography.body2.copy(lineHeight = 18.sp),
                        color = Color.DarkGray,
                        modifier = Modifier
                            .padding(top = 1.dp)
                    )
                },
                trailingIcon = {
                    IconButton(onClick = clearInput, modifier = Modifier.padding(bottom = 120.dp)) {
                        Icon(imageVector = Icons.Default.Clear, contentDescription = "clear")

                    }
                },
                maxLines = 7,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                ),
            )
            Box(
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp, top = 1.dp, bottom = 8.dp)
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp))
                    .background(PrimaryGray),
                contentAlignment = Alignment.CenterEnd
            ) {
                Text(
                    text = "${value.length}/150",
                    style = MaterialTheme.typography.body2,
                    fontSize = 14.sp,
                    color = if (value.length < 150) Black27 else DirtyRed,
                    modifier = Modifier
                        .padding(8.dp)
                        .padding(end = 2.dp),
                    textAlign = TextAlign.Center
                )
            }

        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun WebsiteInput(
    value: String,
    onValueChange: (String) -> Unit,
    clearInput: () -> Unit,
    placeholder: String,
    onPlusHint: (String) -> Unit,
) {
    val kc = LocalSoftwareKeyboardController.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(3.dp, Silver, shape = RoundedCornerShape(24.dp))
            .padding(16.dp)
            .clip(shape = RoundedCornerShape(8.dp))
            .background(Black333),
    ) {
        Text(
            text = "Input field",
            style = MaterialTheme.typography.body2,
            color = WhiteGrey,
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(10.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(225.dp)
                .clip(shape = RoundedCornerShape(8.dp))
                .border(2.dp, Silver, shape = RoundedCornerShape(16.dp))
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
                    .padding(0.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = Color.Transparent,
                    disabledBorderColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent,
                    backgroundColor = PrimaryGray,
                    textColor = Black27,
                    cursorColor = Black27,
                    trailingIconColor = Black27
                ),
                textStyle = MaterialTheme.typography.body2.copy(
                    lineHeight = 18.sp,
                    baselineShift = BaselineShift(-0.07f)
                ),
                placeholder = {
                    Text(
                        text = placeholder,
                        style = MaterialTheme.typography.body2,
                        color = Color.DarkGray,
                        modifier = Modifier
                            .padding(top = 1.dp)
                    )
                },
                trailingIcon = {
                    IconButton(onClick = clearInput, modifier = Modifier.padding(bottom = 106.dp)) {
                        Icon(imageVector = Icons.Default.Clear, contentDescription = "clear")

                    }
                },
                maxLines = 6,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Uri,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        kc?.hide()
                    }
                ),
            )
            Spacer(modifier = Modifier.height(2.dp))
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .clip(shape = RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp))
                    .background(PrimaryGray)
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                item {
                    Box(
                        modifier = Modifier
                            .padding(0.dp)
                            .border(1.dp, Dark, shape = RoundedCornerShape(4.dp))
                            .clip(shape = RoundedCornerShape(4.dp))
                            .background(PrimaryGray)
                            .clickable { onPlusHint("https://") },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "https://",
                            style = MaterialTheme.typography.body2,
                            fontSize = 14.sp,
                            modifier = Modifier.padding(8.dp),
                            textAlign = TextAlign.Center,
                        )
                    }
                }
                item {
                    Spacer(modifier = Modifier.width(5.dp))
                    Box(
                        modifier = Modifier
                            .padding(0.dp)
                            .border(1.dp, Dark, shape = RoundedCornerShape(4.dp))
                            .clip(shape = RoundedCornerShape(4.dp))
                            .background(PrimaryGray)
                            .clickable { onPlusHint("www.") },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "www.",
                            style = MaterialTheme.typography.body2,
                            fontSize = 14.sp,
                            modifier = Modifier.padding(8.dp),
                            textAlign = TextAlign.Center,
                        )
                    }
                }
                item {
                    Spacer(modifier = Modifier.width(5.dp))
                    Button(
                        modifier = Modifier
                            .width(40.dp)
                            .height(40.dp),
                        shape = CircleShape,
                        border = BorderStroke(1.dp, Dark),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = PrimaryGray
                        ),
                        onClick = { onPlusHint("https://www.instagram.com/") }) {
                        Icon(
                            painter = painterResource(id = R.drawable.instagram_icon),
                            contentDescription = "instagram",
                            modifier = Modifier.scale(3f),
                            tint = Black27
                        )
                    }
                }
                item {
                    Spacer(modifier = Modifier.width(5.dp))
                    Button(modifier = Modifier
                        .width(40.dp)
                        .height(40.dp),
                        shape = CircleShape,
                        border = BorderStroke(1.dp, Dark),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = PrimaryGray
                        ),
                        onClick = { onPlusHint("https://www.facebook.com/") }) {
                        Icon(
                            painter = painterResource(id = R.drawable.facebook_icon),
                            contentDescription = "facebook",
                            modifier = Modifier.scale(3f),
                            tint = Black27
                        )
                    }
                }
                item {
                    Spacer(modifier = Modifier.width(5.dp))
                    Button(modifier = Modifier
                        .width(40.dp)
                        .height(40.dp),
                        shape = CircleShape,
                        border = BorderStroke(1.dp, Dark),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = PrimaryGray
                        ),
                        onClick = { onPlusHint("https://www.linkedin.com/in/") }) {
                        Icon(
                            painter = painterResource(id = R.drawable.linkedin_icon),
                            contentDescription = "linkedin",
                            modifier = Modifier.scale(3f),
                            tint = Black27
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun EmailPhoneInput(
    value: String,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType,
    placeholder: String,
) {
    val kc = LocalSoftwareKeyboardController.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(3.dp, Silver, shape = RoundedCornerShape(24.dp))
            .padding(16.dp)
            .clip(shape = RoundedCornerShape(8.dp))
            .background(Black333),
    ) {
        Text(
            text = "Input field",
            style = MaterialTheme.typography.body2,
            color = WhiteGrey,
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(10.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(6.dp))
                .background(Black333),
            contentAlignment = Alignment.BottomEnd
        ) {
            OutlinedTextField(
                value = value,
                onValueChange = onValueChange,

                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = Color.Transparent,
                    disabledBorderColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent,
                    backgroundColor = PrimaryGray,
                    textColor = Black27,
                    cursorColor = Black27,
                    trailingIconColor = Black27
                ),
                modifier = Modifier
                    .fillMaxSize()
                    .border(2.dp, Silver, shape = RoundedCornerShape(16.dp))
                    .padding(8.dp)
                    .clip(shape = RoundedCornerShape(10.dp)),
                textStyle = MaterialTheme.typography.body2.copy(
                    lineHeight = 18.sp,
                    baselineShift = BaselineShift(-0.07f)
                ),
                placeholder = {
                    Text(
                        text = placeholder,
                        style = MaterialTheme.typography.body2,
                        color = Color.DarkGray,
                        modifier = Modifier
                            .padding(top = 1.dp)
                    )
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = keyboardType,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        kc?.hide()
                    }
                ),
            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LocationInput(
    value: String,
    onValueChange: (String) -> Unit,
    secondValue: String,
    onSecondValueChange: (String) -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val kc = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(3.dp, Silver, shape = RoundedCornerShape(24.dp))
            .padding(16.dp)
            .clip(shape = RoundedCornerShape(8.dp))
            .background(Black333),
    ) {
        Text(
            text = "Input field",
            style = MaterialTheme.typography.body2,
            color = WhiteGrey,
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(10.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(6.dp))
                .background(Black333),
            contentAlignment = Alignment.BottomEnd
        ) {
            OutlinedTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier
                    .fillMaxSize()
                    .border(2.dp, Silver, shape = RoundedCornerShape(16.dp))
                    .padding(8.dp)
                    .clip(shape = RoundedCornerShape(10.dp)),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = Color.Transparent,
                    disabledBorderColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent,
                    backgroundColor = PrimaryGray,
                    textColor = Black27,
                    cursorColor = Black27,
                    trailingIconColor = Black27
                ),
                textStyle = MaterialTheme.typography.body2.copy(
                    lineHeight = 18.sp,
                    baselineShift = BaselineShift(-0.07f)
                ),
                placeholder = {
                    Text(
                        text = "Enter latitude ex (49.18373)",
                        style = MaterialTheme.typography.body2.copy(lineHeight = 16.sp),
                        color = Color.DarkGray,
                        modifier = Modifier
                            .padding(top = 1.dp)
                    )
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    autoCorrect = true,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                ),
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(6.dp))
                .background(Black333),
            contentAlignment = Alignment.BottomEnd
        ) {
            OutlinedTextField(
                value = secondValue,
                onValueChange = onSecondValueChange,
                modifier = Modifier
                    .fillMaxSize()
                    .border(2.dp, Silver, shape = RoundedCornerShape(16.dp))
                    .padding(8.dp)
                    .clip(shape = RoundedCornerShape(10.dp)),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = Color.Transparent,
                    disabledBorderColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent,
                    backgroundColor = PrimaryGray,
                    textColor = Black27,
                    cursorColor = Black27,
                    trailingIconColor = Black27
                ),
                textStyle = MaterialTheme.typography.body2.copy(
                    lineHeight = 18.sp,
                    baselineShift = BaselineShift(-0.07f)
                ),
                placeholder = {
                    Text(
                        text = "Enter longitude ex (24.746019)",
                        style = MaterialTheme.typography.body2.copy(lineHeight = 16.sp),
                        color = Color.DarkGray,
                        modifier = Modifier
                            .padding(top = 1.dp)
                    )
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    autoCorrect = true,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        kc?.hide()
                    }
                ),
            )
        }
    }
}
