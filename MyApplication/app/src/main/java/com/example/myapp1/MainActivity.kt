package com.example.myapp1

import android.app.Application
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var subtn :Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        subtn = findViewById(R.id.btn1)

        subtn.setOnClickListener {
            var intent:Intent = Intent(applicationContext,signUp::class.java)
            startActivity(intent)
        }

    }
}


