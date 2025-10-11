package com.example.gamerapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@Composable
fun OTPValidationScreen(navController: NavController) {
    OTPValidationContent(
        onVerify = { code ->
            if (code == "1234") {
                navController.navigate("reset_password") {
                    popUpTo("otp_validation") { inclusive = true } // supprime OTP de la stack
                }
            }
        }
    )
}

@Composable
fun OTPValidationContent(
    onVerify: (String) -> Unit = {}
) {
    var code1 by remember { mutableStateOf("") }
    var code2 by remember { mutableStateOf("") }
    var code3 by remember { mutableStateOf("") }
    var code4 by remember { mutableStateOf("") }

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(snackbarHost = { SnackbarHost(snackbarHostState) }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Enter the code sent to you by email or phone number",
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                color = Color.Gray,
                modifier = Modifier.fillMaxWidth().padding(bottom = 30.dp)
            )

            Spacer(modifier = Modifier.height(30.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                OTPDigitField(code1) { if (it.length <= 1) code1 = it }
                OTPDigitField(code2) { if (it.length <= 1) code2 = it }
                OTPDigitField(code3) { if (it.length <= 1) code3 = it }
                OTPDigitField(code4) { if (it.length <= 1) code4 = it }
            }

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = {
                    val otpCode = code1 + code2 + code3 + code4
                    if (otpCode.length < 4) {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("Please enter the 4-digit code")
                        }
                    } else {
                        onVerify(otpCode)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE91E63)),
                shape = RoundedCornerShape(25.dp)
            ) {
                Text("Verify", fontSize = 18.sp, color = Color.White)
            }
        }
    }
}

@Composable
fun OTPDigitField(value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        textStyle = TextStyle(fontSize = 20.sp, textAlign = TextAlign.Center),
        modifier = Modifier
            .size(60.dp)
            .background(Color.White, shape = RoundedCornerShape(12.dp)),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        visualTransformation = VisualTransformation.None
    )
}
