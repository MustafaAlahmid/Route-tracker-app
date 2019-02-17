package com.example.myapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        details.setOnClickListener {

            val intentA = Intent(this,Main2Activity::class.java)
            startActivity(intentA)
        }


        track.setOnClickListener {

            val intentB = Intent(this,previous::class.java)
            startActivity(intentB)
        }

        location.setOnClickListener {

            val intentC = Intent(this,MapsActivity::class.java)
            startActivity(intentC)
        }

    }
}
