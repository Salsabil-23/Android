package com.example.gamerapp

import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import androidx.compose.material3.*

@Composable
fun SplashScreen(navController: NavController) {
    LaunchedEffect(Unit) {
        delay(2000) // ⏱️ attendre 2 secondes
        navController.navigate("login") {
            popUpTo("splash") { inclusive = true } // pour ne pas revenir au splash
        }
    }

    // Contenu visuel du splash
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.logo_gamer), // ton image dans res/drawable
                contentDescription = "Gamer Logo",
                modifier = Modifier.size(200.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text("GAMER", style = MaterialTheme.typography.headlineMedium)
        }
    }
}
