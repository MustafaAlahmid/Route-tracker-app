package com.example.firebase

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_users.*

class Users : AppCompatActivity() {
        // variables  for the things we will use
    //list view and logout button
    var myAuth = FirebaseAuth.getInstance()
    lateinit var btn:Button
    lateinit var listView: ListView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)



        //connect the variables with their locations
        listView = findViewById(R.id.list)
        FirebaseAuth.getInstance().uid.toString()

        var usersArray = arrayOf("a","b","c","d")


        listView.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,usersArray)

        btn = findViewById(R.id.lobtn)

        //making the sign out function
        btn.setOnClickListener {view ->

            Toast.makeText(this,"logging out ...",Toast.LENGTH_LONG).show()
            signOut()

            myAuth.addAuthStateListener {
                if (myAuth.currentUser == null){
                    this.finish()
                }
            }
        }
    }
    //so simple
    fun signOut(){

        myAuth.signOut()
    }
}
