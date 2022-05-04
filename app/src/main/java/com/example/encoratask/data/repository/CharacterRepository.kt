package com.example.encoratask.data.repository

import com.example.encoratask.data.entity.Character
import com.example.encoratask.data.entity.CharacterList
import com.example.encoratask.data.remote.CharacterService
import timber.log.Timber

// TODO: maybe add persistence with Room?

class CharacterRepository {

    // TODO: this should be resolved using dependency injection
    private val characterService = CharacterService()

    suspend fun getCharacter(id: Int): Character? {
        Timber.d("Requesting character $id")
        val request = characterService.getCharacter(id)

        if (request.isSuccessful) {
            return request.body()!!
        }

        return null
    }

    suspend fun getCharacters(page: Int?): CharacterList? {
        val request = characterService.getCharacters(page)

        if (request.isSuccessful) {
            return request.body()!!
        }

        return null
    }
}