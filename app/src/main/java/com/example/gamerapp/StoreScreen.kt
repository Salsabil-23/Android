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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.unit.Dp
import com.example.gamerapp.R

// Exemple de données
data class Games(val title: String, val price: String, val image: Int)

val gameList = listOf(
    Games("E-football", "$59.99", R.drawable.efootballstore),
    Games("EAFC26", "$49.99", R.drawable.eafc26store),
    Games("GTA6", "$69.99", R.drawable.gta6store),
    Games("Fortnite", "Free", R.drawable.fortnitestore)
)



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StoreScreen() {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Store", color = Color.White, fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFE91E63) // rouge
                )
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        containerColor = Color.White
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            items(gameList) { game ->
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
                        // Image plus grande
                        Image(
                            painter = painterResource(id = game.image),
                            contentDescription = game.title,
                            modifier = Modifier
                                .size(120.dp) // image agrandie
                                .background(Color.LightGray, shape = RoundedCornerShape(12.dp))
                        )

                        Spacer(modifier = Modifier.width(24.dp)) // espacement plus large

                        // Infos à droite
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = game.title,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                color = Color.Black
                            )
                            Text(
                                text = "PS4 Games",
                                fontSize = 14.sp,
                                color = Color.DarkGray
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = game.price,
                                fontSize = 14.sp,
                                color = Color.Black
                            )
                        }

                        // Icône panier
                        IconButton(
                            onClick = {
                                scope.launch {
                                    snackbarHostState.showSnackbar("Added to cart 🛒")
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.ShoppingCart,
                                contentDescription = "Add to Cart",
                                tint = Color(0xFFE91E63), // rouge
                                modifier = Modifier.size(32.dp) // taille icône
                            )
                        }
                    }
                }
            }
        }
    }
}


