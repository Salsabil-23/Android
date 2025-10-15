package com.example.gamerapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import com.example.gamerapp.R


data class Game(val title: String, val price: String, val image: Int)

val favoriteGames = listOf(
    Game("Nexs", "$59.99", R.drawable.efootballstore),
    Game("EAFC26", "$49.99", R.drawable.eafc26store),
    Game("GTA6", "$69.99", R.drawable.gta6store)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen() {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Profile", color = Color.White, fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFE91E63) // rouge
                )
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        containerColor = Color.White // fond blanc
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // Section profil
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "Profile Image",
                    modifier = Modifier.size(100.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text("John Doe", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
                    Text("john@example.com", style = MaterialTheme.typography.bodyMedium, color = Color.DarkGray)
                }
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    onClick = { /* Edit profile */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE91E63)),
                    shape = RoundedCornerShape(25.dp)
                ) {
                    Text("Edit Profile", color = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
            Text("Favorite Games", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))

            // Liste des jeux favoris
            LazyColumn {
                items(favoriteGames) { game ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(6.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Image à gauche
                            Image(
                                painter = painterResource(id = game.image),
                                contentDescription = game.title,
                                modifier = Modifier
                                    .size(100.dp)
                                    .background(Color.LightGray, shape = RoundedCornerShape(12.dp))
                            )

                            Spacer(modifier = Modifier.width(24.dp))

                            // Infos à droite
                            Column(modifier = Modifier.weight(1f)) {
                                Text(game.title, fontWeight = FontWeight.Bold, color = Color.Black)
                                Text("PS4 Games", color = Color.DarkGray)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(game.price, color = Color.Black)
                            }

                            // Icône favoris
                            IconButton(
                                onClick = {
                                    scope.launch {
                                        snackbarHostState.showSnackbar("${game.title} added to favorites ❤️")
                                    }
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Favorite,
                                    contentDescription = "Favorite",
                                    tint = Color(0xFFE91E63),
                                    modifier = Modifier.size(32.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
