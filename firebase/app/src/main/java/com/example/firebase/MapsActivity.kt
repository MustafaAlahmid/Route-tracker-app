package com.example.firebase

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.support.v4.app.ActivityCompat
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.database.FirebaseDatabase
import java.lang.Exception
import java.lang.ref.Reference
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import java.text.SimpleDateFormat
import java.util.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    val sdf = SimpleDateFormat(" hh:mm:ss")
    val currentDate = sdf.format(Date())

    private lateinit var mMap: GoogleMap
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        checkPermession()
    }
    val accessLocation = 213

    fun checkPermession() {
        if (Build.VERSION.SDK_INT >= 23) {

            if (ActivityCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ){
                requestPermissions(arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),accessLocation)
                return
            }
        }

        getLocation()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            accessLocation->{
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    getLocation()
                }else{
                    Toast.makeText(this,"access denided",Toast.LENGTH_LONG).show()
                }
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
    @SuppressLint("MissingPermission")
    fun getLocation(){

        val mylocation = myLocarionListenr()
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,3,15f, mylocation)

        Toast.makeText(this,"access user location",Toast.LENGTH_LONG).show()

        val myThread = MyThread()
        myThread.start()


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
    var myLocation:Location?= null
    inner class myLocarionListenr: LocationListener {
        val us4 = intent.getStringExtra("user")

        constructor(){
            myLocation = Location("me")
            myLocation!!.longitude = 0.0
            myLocation!!.latitude =0.0
        }
        override fun onLocationChanged(location: Location?) {
            myLocation = location
            val longtude = myLocation!!.longitude
            val latetude = myLocation!!.latitude

            val myDatabase:DatabaseReference = FirebaseDatabase.getInstance().getReference("$us4's locations")
            val user = userLoc(currentDate,longtude.toString(),latetude.toString())
            myDatabase.child(user.time).setValue(user).addOnCompleteListener {
                Toast.makeText(this@MapsActivity,"saved",Toast.LENGTH_LONG).show()
            }

        }

        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
        }

        override fun onProviderEnabled(provider: String?) {
        }

        override fun onProviderDisabled(provider: String?) {
        }
    }


    inner class MyThread:Thread{
        //the us4 to take the username for the location
        val us4 = intent.getStringExtra("user")

        constructor():super(){
            //TODO
        }

        override fun run(){
            while (true){

                runOnUiThread{
                    mMap!!.clear()
                    // Add a marker in Sydney and move the camera
                    var sydney = LatLng(myLocation!!.latitude, myLocation!!.longitude)
                    mMap!!.addMarker(MarkerOptions().position(sydney).title(us4))
                    mMap!!.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,14f))

                }

                try {
                    sleep(1000)
                }catch (e:Exception){}

            }
        }
    }

}
class userLoc(val time:String, val long:String, val late:String)



