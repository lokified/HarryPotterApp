package com.loki.harrypotterapp.di

import com.loki.harrypotterapp.data.remote.repository.CharacterListRepositoryImpl
import com.loki.harrypotterapp.data.remote.HarryPotterApi
import com.loki.harrypotterapp.domain.repository.CharacterListRepository
import com.loki.harrypotterapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofitApi(): HarryPotterApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(HarryPotterApi::class.java)
    }

    @Singleton
    @Provides
    fun provideCharacterListRepository(api: HarryPotterApi): CharacterListRepository {
        return CharacterListRepositoryImpl(api)
    }
}