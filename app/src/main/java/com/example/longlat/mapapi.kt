package com.example.longlat
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.longlat.databinding.ActivityMapapiBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
class mapapi : AppCompatActivity(), OnMapReadyCallback {
    companion object {
        val longitude = "longitude"
        val latitude = "latitude"
        val Button = "Button"
    }

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapapiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val button = intent.getStringExtra(Button)

        binding = ActivityMapapiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        // Add a marker in Sydney and move the camera
        val longitudeTarget = intent.getStringExtra(longitude)!!.toDouble()
        val latitudeTarget = intent.getStringExtra(latitude)!!.toDouble()
        val targetLocation = LatLng(latitudeTarget, longitudeTarget)
        mMap.addMarker(MarkerOptions().position(targetLocation).title("Location"))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(targetLocation, 16F));
    }
    
}