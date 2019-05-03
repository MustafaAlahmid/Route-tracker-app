package com.example.firebase

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class details : AppCompatActivity() {
    lateinit var text:TextView
    lateinit var text1:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        text = findViewById(R.id.uname)
        text1 = findViewById(R.id.locationtext)
        val us2 = intent.getStringExtra("id")
        text.text = us2




    }
}
