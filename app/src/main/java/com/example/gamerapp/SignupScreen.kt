package com.example.gamerapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
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
fun SignUpScreen(navController: NavController) {
    SignUpContent(
        onSignup = { fullName, email, password, confirmPassword ->
            if (password == confirmPassword) {
                navController.navigate("home")
            }
        },
        onLogin = { navController.navigate("login") }
    )
}

@Composable
fun SignUpContent(
    onSignup: (String, String, String, String) -> Unit = { _, _, _, _ -> },
    onLogin: () -> Unit = {}
) {
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

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

            Spacer(modifier = Modifier.height(20.dp))

            // Full Name
            OutlinedTextField(
                value = fullName,
                onValueChange = { fullName = it },
                label = { Text("Full Name") },
                leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
                isError = fullName.isEmpty(),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(12.dp)
            )
            if (fullName.isEmpty()) {
                Text("Must not be empty!", color = Color.Red, fontSize = 12.sp)
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Email
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
            if (email.isEmpty()) {
                Text("Must not be empty!", color = Color.Red, fontSize = 12.sp)
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Password
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
            if (password.isEmpty()) {
                Text("Must not be empty!", color = Color.Red, fontSize = 12.sp)
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Confirm Password
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("Confirm Password") },
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                visualTransformation = PasswordVisualTransformation(),
                isError = confirmPassword.isEmpty() || confirmPassword != password,
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(12.dp)
            )
            if (confirmPassword.isEmpty() || confirmPassword != password) {
                Text("Passwords must match!", color = Color.Red, fontSize = 12.sp)
            }

            Spacer(modifier = Modifier.height(20.dp))

            // ✅ Sign Up Button
            Button(
                onClick = {
                    if (fullName.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("Please fill all fields")
                        }
                    } else if (password != confirmPassword) {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("Passwords do not match")
                        }
                    } else {
                        onSignup(fullName, email, password, confirmPassword)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE91E63)),
                shape = RoundedCornerShape(25.dp)
            ) {
                Text("Sign Up", fontSize = 18.sp, color = Color.White)
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text("OR", color = Color.Red)

            Spacer(modifier = Modifier.height(16.dp))

            // ✅ Social Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                // Facebook
                Button(
                    onClick = {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("Facebook signup coming soon!")
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.facebook_btn),
                        contentDescription = "Facebook",
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Facebook", color = Color.Black)
                }

                // Google
                Button(
                    onClick = {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("Google signup coming soon!")
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.google_btn),
                        contentDescription = "Google",
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Google", color = Color.Black)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // ✅ Already have an account
            Text(
                text = "Already have an account? Sign In",
                modifier = Modifier.clickable { onLogin() },
                color = Color.Red,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpPreview() {
    SignUpContent()
}
