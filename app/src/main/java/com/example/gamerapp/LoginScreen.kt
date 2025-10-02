package com.example.gamerapp

import android.R.id.bold
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(navController: NavController) {
    LoginContent(
        onLogin = { email, password ->
            if (email == "test@esprit.tn" && password == "1234") {
                navController.navigate("home")
            }
        },
        onSignup = { navController.navigate("signup")},
        onForgot = { navController.navigate("forgot")}
    )
}
@Composable
fun LoginContent(
    onLogin: (String, String) -> Unit = { _, _ -> },
    onSignup: () -> Unit = {},
    onForgot: () -> Unit = {}
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var rememberMe by remember { mutableStateOf(false) }

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

            // Logo
            Image(
                painter = painterResource(id = R.drawable.logo_gamer),
                contentDescription = "Gamer Logo",
                modifier = Modifier.size(120.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Champ Email
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
                isError = email.isEmpty(),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(12.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            if (email.isEmpty()) {
                Text("Must not be empty!", color = Color.Red, fontSize = 12.sp)
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Champ Password
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                visualTransformation = PasswordVisualTransformation(),
                isError = password.isEmpty(),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(12.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            if (password.isEmpty()) {
                Text("Must not be empty!", color = Color.Red, fontSize = 12.sp)
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = rememberMe,
                        onCheckedChange = { rememberMe = it },
                        colors = CheckboxDefaults.colors(checkedColor = Color(0xFFE91E63))
                    )
                    Text("Remember Me", fontSize = 14.sp)
                }

                Text(
                    text = "Forgot password ?",
                    color = Color.Red,
                    fontSize = 14.sp,
                    modifier = Modifier.clickable { onForgot() }
                )
            }


            Spacer(modifier = Modifier.height(20.dp))

            // Bouton Login
            Button(
                onClick = {
                    if (email.isEmpty() || password.isEmpty()) {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("Please fill all fields")
                        }
                    } else {
                        onLogin(email, password)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE91E63)),
                shape = RoundedCornerShape(25.dp)
            ) {
                Text("Login", fontSize = 18.sp, color = Color.White)
            }

            Spacer(modifier = Modifier.height(50.dp))


            Text("OR", color = Color.Red)

            Spacer(modifier = Modifier.height(50.dp))
// Boutons sociaux
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                // Facebook
                Button(
                    onClick = {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("Facebook login coming soon!")
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1877F2)), // Bleu Facebook
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .height(50.dp)
                        .weight(1f)
                        .padding(end = 8.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.facebook_btn),
                            contentDescription = "Facebook",
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Facebook", color = Color.White, fontSize = 16.sp)
                    }
                }

                // Google
                Button(
                    onClick = {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("Google login coming soon!")
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .height(50.dp)
                        .weight(1f)
                        .padding(start = 8.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.google_btn),
                            contentDescription = "Google",
                            modifier = Modifier.size(24.dp) // ✅ taille homogène
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Google", color = Color.Black, fontSize = 16.sp)
                    }
                }
            }


            Spacer(modifier = Modifier.height(50.dp))

            // Register Now
            Text(
                text = "Don’t have an account? Register Now",
                modifier = Modifier.clickable { onSignup() },
                color = Color.Red,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    LoginContent()
}
