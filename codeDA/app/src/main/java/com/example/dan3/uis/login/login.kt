package com.example.dan3.uis.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen(
    onClick: (String) -> Unit,
    onRegisterClick: () -> Unit = {}
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp)
    ) {
        Text(
            text = "Đăng Kí",
            modifier = Modifier
                .align(Alignment.TopEnd)
                .clickable { onRegisterClick() },
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Đăng Nhập",
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(bottom = 32.dp),
                color = Color.Black
            )

            outlineTextForm(
                text = username,
                "Tên Đăng Nhập",
                onTextChange = { username = it }
            )
            outlineTextForm(
                text = password,
                "Mật Khẩu",
                onTextChange = { username = it }
            )

            Button(
                onClick = { onClick(username) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(top = 16.dp),
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Đăng Nhập",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun RegisterScreen(
    onClick: (String) -> Unit,
    onLoginClick: () -> Unit = {}
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp)
    ) {
        Text(
            text = "Đăng Nhập",
            modifier = Modifier
                .align(Alignment.TopEnd)
                .clickable { onLoginClick() },
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Đăng Kí",
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(bottom = 32.dp),
                color = Color.Black
            )

            outlineTextForm(
                text = username,
                placeholder = "Tên Đăng Nhập",
                onTextChange = { username = it }
            )
            outlineTextForm(
                text = password,
                placeholder = "Mật Khẩu",
                onTextChange = { password = it }
            )
            outlineTextForm(
                text = phone,
                placeholder = "SĐT",
                onTextChange = { phone = it }
            )
            outlineTextForm(
                text = email,
                placeholder = "Gmail",
                onTextChange = { email = it }
            )

            Button(
                onClick = { onClick(username) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .height(66.dp),
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Đăng Kí",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun outlineTextForm(text:String,placeholder:String, onTextChange: (String) -> Unit){
    OutlinedTextField(
        value = text,
        onValueChange = {onTextChange(it)},
        placeholder = { Text(text = placeholder, color = Color.LightGray) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        shape = RectangleShape,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Black,
            unfocusedBorderColor = Color.Black,
            cursorColor = Color.Black,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
        ),
        singleLine = true
    )
}
@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    RegisterScreen(onClick = {})
}
