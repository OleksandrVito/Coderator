package ua.vitolex.qrcodegenerator_qrcodescan.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ua.vitolex.qrcodegenerator_qrcodescan.ui.theme.*

@Composable
fun TextInput(value: String, onValueChange: (String) -> Unit, clearInput: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .height(250.dp)
            .border(3.dp, Silver, shape = RoundedCornerShape(30.dp))
            .padding(vertical = 25.dp, horizontal = 18.dp)
            .clip(shape = RoundedCornerShape(26.dp))
            .background(Black333),
        contentAlignment = Alignment.BottomEnd
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxSize()
                .border(2.dp, Silver, shape = RoundedCornerShape(25.dp))
                .padding(8.dp)
                .clip(shape = RoundedCornerShape(20.dp)),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = PrimaryGray,
                textColor = Black27,
                focusedIndicatorColor = Color.Transparent,
                cursorColor = Black27,
            ),
            textStyle = MaterialTheme.typography.body2.copy(lineHeight = 18.sp),
            placeholder = {
                Text(
                    text = "Enter text",
                    style = MaterialTheme.typography.body2,
                    color = Color.DarkGray
                )
            },
            trailingIcon = {
                IconButton(onClick = clearInput, modifier = Modifier.padding(bottom = 140.dp)) {
                    Icon(imageVector = Icons.Default.Clear, contentDescription = "clear")

                }
            },
            maxLines = 7,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
            ),
        )
        Text(
            text = "${value.length}/150",
            style = MaterialTheme.typography.body2,
            fontSize = 14.sp,
            color = if (value.length < 150) Black27 else DirtyRed,
            modifier = Modifier
                .padding(bottom = 20.dp, end = 5.dp)
                .width(100.dp),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun EmailInput(
    value: String,
    onValueChange: (String) -> Unit,
    clearInput: () -> Unit,
    valueEmail: String,
    onValueEmailChange: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .height(324.dp)
            .border(3.dp, Silver, shape = RoundedCornerShape(30.dp))
            .padding(vertical = 25.dp, horizontal = 18.dp)
            .clip(shape = RoundedCornerShape(26.dp))
            .background(Black333),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .clip(shape = RoundedCornerShape(26.dp))
                .background(Black333),
            contentAlignment = Alignment.BottomEnd
        ) {
            OutlinedTextField(
                value = valueEmail,
                onValueChange = onValueEmailChange,
                modifier = Modifier
                    .fillMaxSize()
                    .border(2.dp, Silver, shape = RoundedCornerShape(25.dp))
                    .padding(8.dp)
                    .clip(shape = RoundedCornerShape(20.dp)),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = PrimaryGray,
                    textColor = Black27,
                    focusedIndicatorColor = Color.Transparent,
                    cursorColor = Black27,
                ),
                textStyle = MaterialTheme.typography.body2.copy(lineHeight = 18.sp),
                placeholder = {
                    Text(
                        text = "Enter email",
                        style = MaterialTheme.typography.body2,
                        color = Color.DarkGray
                    )
                },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                ),
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .clip(shape = RoundedCornerShape(26.dp))
                .background(Black333),
            contentAlignment = Alignment.BottomEnd
        ) {
            OutlinedTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier
                    .fillMaxSize()
                    .border(2.dp, Silver, shape = RoundedCornerShape(25.dp))
                    .padding(8.dp)
                    .clip(shape = RoundedCornerShape(20.dp)),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = PrimaryGray,
                    textColor = Black27,
                    focusedIndicatorColor = Color.Transparent,
                    cursorColor = Black27,
                ),
                textStyle = MaterialTheme.typography.body2.copy(lineHeight = 18.sp),
                placeholder = {
                    Text(
                        text = "Enter message",
                        style = MaterialTheme.typography.body2,
                        color = Color.DarkGray
                    )
                },
                trailingIcon = {
                    IconButton(onClick = clearInput, modifier = Modifier.padding(bottom = 140.dp)) {
                        Icon(imageVector = Icons.Default.Clear, contentDescription = "clear")

                    }
                },
                maxLines = 7,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                ),
            )
            Text(
                text = "${value.length}/150",
                style = MaterialTheme.typography.body2,
                fontSize = 14.sp,
                color = if (value.length < 150) Black27 else DirtyRed,
                modifier = Modifier
                    .padding(bottom = 20.dp, end = 5.dp)
                    .width(100.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun PhoneInput(
    value: String,
    onValueChange: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .height(114.dp)
            .border(3.dp, Silver, shape = RoundedCornerShape(30.dp))
            .padding(vertical = 25.dp, horizontal = 18.dp)
            .clip(shape = RoundedCornerShape(26.dp))
            .background(Black333),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .clip(shape = RoundedCornerShape(26.dp))
                .background(Black333),
            contentAlignment = Alignment.BottomEnd
        ) {
            OutlinedTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier
                    .fillMaxSize()
                    .border(2.dp, Silver, shape = RoundedCornerShape(25.dp))
                    .padding(8.dp)
                    .clip(shape = RoundedCornerShape(20.dp)),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = PrimaryGray,
                    textColor = Black27,
                    focusedIndicatorColor = Color.Transparent,
                    cursorColor = Black27,
                ),
                textStyle = MaterialTheme.typography.body2.copy(lineHeight = 18.sp),
                placeholder = {
                    Text(
                        text = "Enter phone number",
                        style = MaterialTheme.typography.body2,
                        color = Color.DarkGray
                    )
                },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone,
                ),
            )
        }
    }
}

@Composable
fun SmsInput(
    value: String,
    onValueChange: (String) -> Unit,
    clearInput: () -> Unit,
    valuePhone: String,
    onValuePhoneChange: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .height(324.dp)
            .border(3.dp, Silver, shape = RoundedCornerShape(30.dp))
            .padding(vertical = 25.dp, horizontal = 18.dp)
            .clip(shape = RoundedCornerShape(26.dp))
            .background(Black333),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .clip(shape = RoundedCornerShape(26.dp))
                .background(Black333),
            contentAlignment = Alignment.BottomEnd
        ) {
            OutlinedTextField(
                value = valuePhone,
                onValueChange = onValuePhoneChange,
                modifier = Modifier
                    .fillMaxSize()
                    .border(2.dp, Silver, shape = RoundedCornerShape(25.dp))
                    .padding(8.dp)
                    .clip(shape = RoundedCornerShape(20.dp)),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = PrimaryGray,
                    textColor = Black27,
                    focusedIndicatorColor = Color.Transparent,
                    cursorColor = Black27,
                ),
                textStyle = MaterialTheme.typography.body2.copy(lineHeight = 18.sp),
                placeholder = {
                    Text(
                        text = "Enter phone number",
                        style = MaterialTheme.typography.body2,
                        color = Color.DarkGray
                    )
                },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone,
                ),
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .clip(shape = RoundedCornerShape(26.dp))
                .background(Black333),
            contentAlignment = Alignment.BottomEnd
        ) {
            OutlinedTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier
                    .fillMaxSize()
                    .border(2.dp, Silver, shape = RoundedCornerShape(25.dp))
                    .padding(8.dp)
                    .clip(shape = RoundedCornerShape(20.dp)),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = PrimaryGray,
                    textColor = Black27,
                    focusedIndicatorColor = Color.Transparent,
                    cursorColor = Black27,
                ),
                textStyle = MaterialTheme.typography.body2.copy(lineHeight = 18.sp),
                placeholder = {
                    Text(
                        text = "Enter message",
                        style = MaterialTheme.typography.body2,
                        color = Color.DarkGray
                    )
                },
                trailingIcon = {
                    IconButton(onClick = clearInput, modifier = Modifier.padding(bottom = 140.dp)) {
                        Icon(imageVector = Icons.Default.Clear, contentDescription = "clear")

                    }
                },
                maxLines = 7,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                ),
            )
            Text(
                text = "${value.length}/150",
                style = MaterialTheme.typography.body2,
                fontSize = 14.sp,
                color = if (value.length < 150) Black27 else DirtyRed,
                modifier = Modifier
                    .padding(bottom = 20.dp, end = 5.dp)
                    .width(100.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SmsInputPreview() {
    var inputValue by remember { mutableStateOf("") }
    var inputEmailValue by remember { mutableStateOf("") }
    PhoneInput(
        value = inputValue,
        onValueChange = { inputValue = it },

       )
}