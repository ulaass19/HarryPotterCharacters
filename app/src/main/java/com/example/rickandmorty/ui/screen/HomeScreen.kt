package com.example.rickandmorty.ui.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.example.rickandmorty.R
import com.example.rickandmorty.data.model.Characters
import com.example.rickandmorty.viewmodel.HomeViewModel


@Composable
fun ScreenRouter() {

    val navController = rememberNavController()
    val viewModel : HomeViewModel = viewModel()

    NavHost(navController, startDestination = "home") {
        composable("home") {
            // Ana ekranın içeriği
            HomeScreen(navController = navController,viewModel)
        }
        composable("detail/{characterName}/{characterActor}") { backStackEntry ->
            // Tıklanan karakterin adını al
            val characterName =
                backStackEntry.arguments?.getString("characterName") ?: ""

            val characterActor =
                backStackEntry.arguments?.getString("characterActor") ?: ""

            // Detay ekranını göster
            DetailScreen(characterName = characterName, characterActor = characterActor)
        }
    }
    
}

@Composable
fun HomeScreen(
    navController : NavHostController,
    viewModel: HomeViewModel
) {

    //val homeViewModel = viewModel(modelClass = HomeViewModel::class.java)
    val state by viewModel.state.collectAsState()

    val context = LocalContext.current

    /*LazyColumn {
        if (state.isEmpty()) {
            item {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(align = Alignment.Center)
                )
            }
        }

        items(state) {character: Characters ->
            CardImage(character = character)
        }

    }*/
    
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {

        LazyVerticalGrid(
            columns = GridCells.Fixed(2)
        ) {
            if (state.isEmpty()) {
                item {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(align = Alignment.Center)
                    )
                }
            } else {

                items(state) {character: Characters ->
                    CardImage(character = character) {
                        val text = "You clicked : ${character.name}"
                        val duration = Toast.LENGTH_LONG

                        val toast = Toast.makeText(context, text, duration)
                        toast.show()

                        navController.navigate("detail/${character.name}/${character.actor}")
                    }
                }

            }

        }

    }
    
}

@Composable
fun CardImage(character: Characters,onItemClick : (String) -> (Unit)) {

    val imagePainter = rememberImagePainter(data = character.image)

    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .padding(16.dp)
            .clickable {
                onItemClick(character.name)
            }
    ) {

        Box {

            if (character.image.equals("")) {

                Image(
                    painter = painterResource(id = R.drawable.placeholder),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.FillBounds
                )

                Surface(
                    color = MaterialTheme.colorScheme.onSurface.copy(.3f),
                    modifier = Modifier.align(Alignment.BottomCenter),
                    contentColor = MaterialTheme.colorScheme.surface
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp)
                    ) {

                        Text(
                            text = "${character.name} ",
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "${character.actor} ",
                            fontSize = 12.sp
                        )
                    }

                }

            } else {

                Image(
                    painter = imagePainter,
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.FillBounds
                )

                Surface(
                    color = MaterialTheme.colorScheme.onSurface.copy(.3f),
                    modifier = Modifier.align(Alignment.BottomCenter),
                    contentColor = MaterialTheme.colorScheme.surface
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp)
                    ) {

                        Text(
                            text = "${character.name} ",
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "${character.actor} ",
                            fontSize = 12.sp
                        )
                    }

                }

            }


        }

    }

}