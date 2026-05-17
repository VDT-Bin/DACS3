package com.example.dan3.uis.auth

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.dan3.uis.UiEvent

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier
    ,navController: NavController
    ,viewModel: AuthViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val authState = viewModel.authState.collectAsState()
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect {
            event ->
            when(event){
                is UiEvent.NavigateTo -> {
                    navController.navigate(event.route)
                }
                is UiEvent.ShowToast -> {
                    Toast.makeText(context,event.message,Toast.LENGTH_SHORT).show()
                }
                else ->{}
            }
        }
    }

    Box(
           modifier = modifier.fillMaxSize()
            .background(Color.White)
            .padding(24.dp)
    ) {
        Text(
            text = "Đăng Kí",
            modifier = modifier
                .align(Alignment.TopEnd)
                .clickable {
                    navController.navigate("register")
                },
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black
        )

        Column(
            modifier = modifier
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
            Spacer(modifier = Modifier.height(20.dp))
            outlineTextForm(
                text = password,
                "Mật Khẩu",
                onTextChange = { username = it }
            )

            Button(
                onClick = {},
                modifier = modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    ,shape = RectangleShape,
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
    modifier: Modifier = Modifier
    ,navController: NavController
    ,viewModel: AuthViewModel = hiltViewModel()
) {
    /*
    *( ở repository) - > giao diện nhận
    * asStateFlow
    * //val authState = viewModel.(name state).collectAsState()
    * or  authState by viewModel.(name state).collectAsState()
    * asSharedFlow()
    * // LaunchedEffect(Unit){
    * viewModel.uiEvent.collect{event ->
          when(event){
            is UiEvent.NavigateTo -> {
                navController.navigate(
                    event.route
                )
            }
    *
    *   }
    * }
    * */
    val context = LocalContext.current

    val authState = viewModel.authState.collectAsState()
    var name by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }


    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect {
            event ->
            when(event){
                is UiEvent.NavigateTo -> {
                    navController.navigate(event.route)
                }
                is UiEvent.ShowToast -> Toast.makeText(context,event.message,Toast.LENGTH_SHORT).show()
                else ->{}
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp)
    ) {
        Text(
            text = "Đăng Nhập",
            modifier = Modifier
                .align(Alignment.TopEnd)
                .clickable {
                    navController.navigate("login")
                },
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

            Spacer(modifier = Modifier.height(20.dp))

            outlineTextForm(
                text = password,
                placeholder = "Mật Khẩu",
                onTextChange = { password = it }
            )
            Spacer(modifier = Modifier.height(20.dp))
            outlineTextForm(
                text = phone,
                placeholder = "SĐT",
                onTextChange = { phone = it }
            )
            Spacer(modifier = Modifier.height(20.dp))
            outlineTextForm(
                text = email,
                placeholder = "Gmail",
                onTextChange = { email = it }
            )
            Spacer(modifier = Modifier.height(30.dp))
            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
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
            ,shape = RectangleShape,
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