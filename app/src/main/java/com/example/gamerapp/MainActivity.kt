package com.example.gamerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gamerapp.ForgotPasswordScreen
import com.example.gamerapp.OTPValidationScreen
import com.example.gamerapp.ResetPasswordScreen
import com.example.gamerapp.ui.theme.GamerAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GamerAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GamerApp()
                }
            }
        }
    }
}

@Composable
fun GamerApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(navController) }
        composable("main") { MainScreen() } // MainScreen avec NewsScreen par défaut
        composable("signup") { SignUpScreen(navController) }
        composable("forgot_password") { ForgotPasswordScreen(navController) }
        composable("otp_validation") { OTPValidationScreen(navController) }
        composable("reset_password") { ResetPasswordScreen(navController) }
    }
}
