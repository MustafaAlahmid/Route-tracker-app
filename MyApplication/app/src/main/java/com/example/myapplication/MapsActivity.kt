package com.example.myapplication

import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.lang.Exception

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        checkPermission()
    }
    val AccessLocation = 123

    fun checkPermission(){

        if (Build.VERSION.SDK_INT >= 23){

            if (ActivityCompat.checkSelfPermission(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){

                requestPermissions(arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),AccessLocation)
                return
            }
        }
        getUserLOcation()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){

            AccessLocation ->{
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    getUserLOcation()
                }else{
                    Toast.makeText(this,"accese location denied ",Toast.LENGTH_LONG).show()

                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

    }



    fun getUserLOcation(){

        Toast.makeText(this,"accese location now ",Toast.LENGTH_LONG).show()
        //todo access user location

        val mylocation = myLocationListiner()
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationManager.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            1,
            1f,
            mylocation
        )

        val mythread = MyThread()
        mythread.start()
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap


    }

    var myLocation:Location?=null
    inner class myLocationListiner:LocationListener{
        constructor(){
            myLocation = Location("me")
            myLocation!!.longitude = 0.0
            myLocation!!.latitude = 0.0
        }
        override fun onLocationChanged(location: Location?) {

            myLocation = location
        }

        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
        }

        override fun onProviderEnabled(provider: String?) {
        }

        override fun onProviderDisabled(provider: String?) {
        }


    }

    inner class MyThread:Thread{

        constructor():super(){
            //todo later see old location
        }
        override fun run(){

            while (true) try {

                runOnUiThread{
                    mMap!!.clear()
                    val sydney = LatLng(myLocation!!.latitude,myLocation!!.longitude)
                    mMap.addMarker(MarkerOptions()
                        .position(sydney)
                        .title("me"))
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
                }



                Thread.sleep(1000)
            }catch (Ex:Exception){}
        }
    }
}


