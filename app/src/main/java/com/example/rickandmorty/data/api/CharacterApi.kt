package com.example.rickandmorty.data.api

import com.example.rickandmorty.data.model.Characters
import retrofit2.http.GET

interface CharacterApi {
    @GET(ApiConstants.END_POINTS)
    suspend fun getCharacter():List<Characters>
}