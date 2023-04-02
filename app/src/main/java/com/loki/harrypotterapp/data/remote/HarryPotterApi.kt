package com.loki.harrypotterapp.data.remote

import com.loki.harrypotterapp.domain.models.CharacterItem
import retrofit2.http.GET

interface HarryPotterApi {

    @GET("characters")
    suspend fun getCharacterList(): List<CharacterItem>
}