package com.example.firebase

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import android.content.Intent
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_users.*


class Users : AppCompatActivity() {

    private lateinit var listView: ListView
    var myAuth = FirebaseAuth.getInstance()
    lateinit var btn:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)


        listView = findViewById(R.id.list)
        val list = mutableListOf<Users1>()
        list.add(Users1("mustafa", "azzurri", "spb"))
        list.add(Users1("dsdvsd", "adsvsi", "spb"))
        list.add(Users1("mustafa", "azzurri", "spb"))

        val adapter = myAdapterList(this, R.layout.my_list_item, list)
        listView.adapter = adapter

        btn = findViewById(R.id.lobtn)

        //making the sign out function
        btn.setOnClickListener { view ->

            Toast.makeText(this, "logging out ...", Toast.LENGTH_LONG).show()
            myAuth.signOut()

            myAuth.addAuthStateListener {
                if (myAuth.currentUser == null) {
                    this.finish()
                }


            }
        }

    }
}
