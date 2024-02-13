package com.example.rickandmorty.repository

import com.example.rickandmorty.data.api.CharacterApi
import com.example.rickandmorty.data.model.Characters
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val characterApi: CharacterApi
) {
    suspend fun getCharacters() : List<Characters> {
        return characterApi.getCharacter()
    }
}