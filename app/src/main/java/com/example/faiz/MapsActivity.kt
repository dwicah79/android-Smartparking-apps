package com.example.faiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.faiz.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        
        val malioboro = LatLng(-7.792390377963261, 110.36591036856137)
        val alkid = LatLng(-7.811664184835047, 110.3631882378717)
        val bukitbintang = LatLng(-7.827473564897634, 110.49503174022487)
        val prawirotaman = LatLng(-7.818887165022111, 110.37055916917122)
        val diy = LatLng(-7.79525500580557, 110.3683206627526)

        //membuat tanda di malioboro
        mMap.addMarker(MarkerOptions().position(malioboro).title("Marker in Malioboro"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(malioboro))

        //membuat tanda di malioboro
        mMap.addMarker(MarkerOptions().position(alkid).title("Alun Alun Kidul"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(alkid))

        //membuat tanda di malioboro
        mMap.addMarker(MarkerOptions().position(bukitbintang).title("Bukit Bintang"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(bukitbintang))

        //membuat tanda di malioboro
        mMap.addMarker(MarkerOptions().position(prawirotaman).title("Prawirotaman"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(prawirotaman))

        //membuat zoom agar peta tidak terlalu jauh
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(diy, 13f))
    }
}