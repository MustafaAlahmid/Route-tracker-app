package com.example.firebase

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class details : AppCompatActivity() {
    lateinit var text:TextView
    lateinit var btn1:Button
    lateinit var btn2:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        btn1 = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)


        text = findViewById(R.id.uname)
        val us2 = intent.getStringExtra("id")
        text.text = us2
        btn1.setOnClickListener {

            val userid = us2
            var intent = Intent(this, MapsActivity2::class.java)
            intent.putExtra("id",userid)
            startActivity(intent)


        }

        btn2.setOnClickListener {
            val userid = us2

            var intent = Intent(this, user_info::class.java)
            intent.putExtra("id",userid)
            startActivity(intent)


        }



    }
}
