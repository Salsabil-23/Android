package com.example.gamerapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
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
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import com.example.gamerapp.R

data class News(val title: String, val description: String, val image: Int)

val newsList = listOf(
    News("Top 10 Gaming News of the day", "Here’s the latest news from the gaming and e-sports world.", R.drawable.efootball),
    News("Fortnite", "Action and adventure game on Nintendo Switch.", R.drawable.fortnite),
    News("EAFC26", "Football like never before!", R.drawable.eafc26),
    News("GTA6", "The most anticipated action game ever.", R.drawable.gta6),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreen() {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("News", color = Color.White, fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFE91E63)
                ),
                actions = {
                    IconButton(onClick = { /* TODO: Notifications */ }) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "Notifications",
                            tint = Color.White
                        )
                    }
                }
            )
        },
        containerColor = Color(0xFFF8F8F8)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
            ) {
                items(newsList) { news ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(6.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Image(
                                painter = painterResource(id = news.image),
                                contentDescription = news.title,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(180.dp)
                                    .background(Color.LightGray, shape = RoundedCornerShape(12.dp))
                            )

                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                news.title,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                news.description,
                                fontSize = 14.sp,
                                color = Color.DarkGray
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            Button(
                                onClick = {
                                    // utiliser le scope pour lancer une coroutine
                                    scope.launch {
                                        snackbarHostState.showSnackbar("Coming soon ❤️")
                                    }
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE91E63)),
                                shape = RoundedCornerShape(25.dp),
                                modifier = Modifier.align(Alignment.End)
                            ) {
                                Text("Show More", color = Color.White)
                            }
                        }
                    }
                }
            }


        }
    }
}
