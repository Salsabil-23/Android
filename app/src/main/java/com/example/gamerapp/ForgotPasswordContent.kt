package com.example.gamerapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@Composable
fun ForgotPasswordScreen(navController: NavController) {
    ForgotPasswordContent(
        onSend = { emailOrPhone ->
            navController.navigate("login")
        }
    )
}

@Composable
fun ForgotPasswordContent(
    onSend: (String) -> Unit = {}
) {
    var emailOrPhone by remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(snackbarHost = { SnackbarHost(snackbarHostState) }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            // ✅ Logo
            Image(
                painter = painterResource(id = R.drawable.logo_gamer),
                contentDescription = "Gamer Logo",
                modifier = Modifier.size(120.dp)
            )

            Spacer(modifier = Modifier.height(30.dp))

            // ✅ Texte d’instruction
            Text(
                text = "Please enter your email to reset your password",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(20.dp))

            // ✅ Champ Email / Phone
            OutlinedTextField(
                value = emailOrPhone,
                onValueChange = { emailOrPhone = it },
                label = { Text("Email or Phone number") },
                leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
                isError = emailOrPhone.isEmpty(),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(12.dp)
            )
            if (emailOrPhone.isEmpty()) {
                Text("Must not be empty!", color = Color.Red, fontSize = 12.sp)
            }

            Spacer(modifier = Modifier.height(30.dp))

            // ✅ Bouton Send
            Button(
                onClick = {
                    if (emailOrPhone.isEmpty()) {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("Please enter email or phone")
                        }
                    } else {
                        onSend(emailOrPhone)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE91E63)),
                shape = RoundedCornerShape(25.dp)
            ) {
                Text("Submit", fontSize = 18.sp, color = Color.White)
            }

            Spacer(modifier = Modifier.height(30.dp))
            
            Text("OR", color = Color.Red)

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = {

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE91E63)),
                shape = RoundedCornerShape(25.dp)
            ) {
                Text("Send SMS", fontSize = 18.sp, color = Color.White)
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun ForgotPasswordPreview() {
    ForgotPasswordContent()
}
