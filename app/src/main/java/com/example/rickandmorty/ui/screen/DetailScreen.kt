package com.example.rickandmorty.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter

@Composable
fun DetailScreen(
    characterName : String,
    characterActor: String,
    characterImage : String
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {

        val painter = rememberImagePainter(data = characterImage)
        
        Image(
            painter = painter,
            contentDescription = characterName,
            modifier = Modifier.size(200.dp,300.dp)
        )

        Text(
            text = "Real Name : ${characterActor}",
            fontSize = 18.sp
        )

        Text(
            text = "In the name of the movie : ${characterName}",
            fontSize = 18.sp,
            maxLines = 1
        )
    }
}