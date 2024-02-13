package com.example.rickandmorty.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun DetailScreen(
    characterName : String,
    characterActor: String
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {

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