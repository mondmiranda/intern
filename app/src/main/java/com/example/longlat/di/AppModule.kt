package com.example.longlat.di

import com.example.longlat.Network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponentManager::class)
class AppModule {

    @Provides
    @Singleton
    fun getBaseUrl():String ="https://api.openweathermap.org/data/2.5/"

    @Provides
    @Singleton
    fun  getRetrofitBuilder(baseUrl:String): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    @Provides
    fun getApiSevice(retrofit: Retrofit):ApiService =
        retrofit.create(ApiService::class.java)
}