package com.example.longlat.Model

data class City(
    val weather:Weather,
    val main:Main,
    val wind:Wind,
    val name:String
) {
}

class Weather(
    val description:String
) {

}

data class Main(
    val temp:Double,
    val humidity:Int
)
data class Wind(
    val speed:Float,
    val deg:Int,


){

}


