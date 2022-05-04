package com.example.encoratask.data.remote

import com.example.encoratask.data.entity.Character
import com.example.encoratask.data.entity.CharacterList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

// https://rickandmortyapi.com/documentation/#rest
interface CharacterApi {
    @GET("character")
    suspend fun getCharacters(@Query("page") page: Int?): Response<CharacterList>

    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id: Int): Response<Character>
}