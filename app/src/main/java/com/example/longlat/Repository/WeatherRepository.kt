package com.example.longlat.Repository

import com.example.longlat.Model.City
import com.example.longlat.Network.ApiServicelmp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class WeatherRepository @Inject constructor(private val ApiServiceImp: ApiServicelmp) {

    fun getCityData(city:String): Flow<City> = flow {
        val response = ApiServiceImp.getCityData(city, "2a787775d9187a0f6fd3fb39eda72b21")
        emit(response)
    }.flowOn(Dispatchers.IO)
        .conflate()
}