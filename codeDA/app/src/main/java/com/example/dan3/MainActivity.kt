package com.example.dan3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dan3.ui.theme.DAN3Theme
import com.example.dan3.uis.auth.LoginScreen
import com.example.dan3.uis.auth.RegisterScreen
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DAN3Theme {
               Scaffold(modifier = Modifier.fillMaxSize()) {
                   innerPadding ->
                       AppNavigation(Modifier.padding(innerPadding))
                   }
                }
            }
        }
    }
@Composable
fun AppNavigation(modifier: Modifier = Modifier){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login", modifier = modifier) {

        composable("login") {
            LoginScreen(modifier,navController
            )
        }
        composable("register") {
            RegisterScreen(modifier,navController)
        }
    }
}