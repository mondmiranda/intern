package com.example.longlat.Network

import com.example.longlat.Model.City

class ApiServicelmp constructor(private  val apiService: ApiService){

    suspend fun  getCityData(city:String,appId:String): City =
        apiService.getCityData(city,appId)
}