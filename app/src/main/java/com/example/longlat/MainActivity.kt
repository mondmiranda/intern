package com.example.longlat

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.longlat.ViewModel.WeatherViewModel
import com.example.longlat.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val weatherViewModel:WeatherViewModel by viewModels()
    @FlowPreview
    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        weatherViewModel.getCityData()
        initListener()
        weatherViewModel.weatherResponse.observe(this, Observer {response->

            if(response.weather.description == "clear sky" || response.weather.description == "mist"){
                Glide.with(this)
                    .load(R.drawable.clouds)
                    .into(binding.image)
            }else
                if(response.weather.description == "haze" || response.weather.description == "overcast clouds" || response.weather.description == "fog" ){
                    Glide.with(this)
                        .load(R.drawable.haze)
                        .into(binding.image)
                }else
                    if(response.weather.description == "rain"){
                        Glide.with(this)
                            .load(R.drawable.rain)
                            .into(binding.image)
                    }
            binding.description.text=response.weather.description
            binding.name.text=response.name
            binding.degree.text=response.wind.deg.toString()
            binding.temp.text=response.main.temp.toString()
            binding.humidity.text=response.main.humidity.toString()

        })
    }

    @ExperimentalCoroutinesApi
    private fun initListener()
    {
       (object : SearchView.OnQueryTextListener, android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { weatherViewModel.setSearchQuery(it) }
                Log.d("main", "onQueryTextChange: $query")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return true
            }

        })


        Button.setOnClickListener {
            val longitude = longitude.text.toString()
            val latitude = latitude.text.toString()
            val button = Button.text.toString()

            val intent = Intent(this@MainActivity, mapapi::class.java)
            intent.putExtra(mapapi.longitude, longitude)
            intent.putExtra(mapapi.latitude, latitude)
            intent.putExtra(mapapi.Button, button)
            startActivity(intent)
        }

    }


}



