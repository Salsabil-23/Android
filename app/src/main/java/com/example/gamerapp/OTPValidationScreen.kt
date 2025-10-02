package com.example.gamerapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch


@Composable
fun OTPValidationScreen(navController: NavController) {
    OTPValidationContent(
        onVerify = { code ->
            if (code == "1234") { // ex: OTP correct
                navController.navigate("home")
            }
        },
        onResend = {
            // logique pour renvoyer l'OTP
        }
    )
}

@Composable
fun OTPValidationContent(
    onVerify: (String) -> Unit = {},
    onResend: () -> Unit = {}
) {
    var code1 by remember { mutableStateOf("") }
    var code2 by remember { mutableStateOf("") }
    var code3 by remember { mutableStateOf("") }
    var code4 by remember { mutableStateOf("") }

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    val otpCode = code1 + code2 + code3 + code4

    Scaffold(snackbarHost = { SnackbarHost(snackbarHostState) }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // ✅ Titre
            Text(
                text = "Enter the code sent to you by email or phone number",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color.Gray,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 30.dp)
            )


            Spacer(modifier = Modifier.height(30.dp))

            // ✅ Champs OTP (4 digits)
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OTPDigitField(value = code1, onValueChange = { if (it.length <= 1) code1 = it })
                OTPDigitField(value = code2, onValueChange = { if (it.length <= 1) code2 = it })
                OTPDigitField(value = code3, onValueChange = { if (it.length <= 1) code3 = it })
                OTPDigitField(value = code4, onValueChange = { if (it.length <= 1) code4 = it })
            }

            Spacer(modifier = Modifier.height(30.dp))

            // ✅ Bouton Verify
            Button(
                onClick = {
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

            Spacer(modifier = Modifier.height(20.dp))

            // ✅ Texte "Didn't receive the code?"
            Text(
                text = "Didn't receive the code?",
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(10.dp))

            // ✅ Bouton Resend
            Text(
                text = "Resend",
                fontSize = 16.sp,
                color = Color.Red,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable { onResend() }
            )
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

@Preview(showBackground = true)
@Composable
fun OTPValidationPreview() {
    OTPValidationContent()
}
