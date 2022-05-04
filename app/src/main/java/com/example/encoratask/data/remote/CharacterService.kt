package com.example.encoratask.data.remote

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CharacterService {

    // TODO: this should be resolved using dependency injection
    private val gson: Gson = GsonBuilder().create()
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/api/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
    private val characterApi: CharacterApi = retrofit.create(CharacterApi::class.java)

    suspend fun getCharacters(page: Int? = null) = characterApi.getCharacters(page)
    suspend fun getCharacter(id: Int) = characterApi.getCharacter(id)
}