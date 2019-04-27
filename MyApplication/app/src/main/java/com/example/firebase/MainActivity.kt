package com.example.firebase

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    //we make variables for text views, buttons and for firebase authenticating
    var myAuth = FirebaseAuth.getInstance()
    lateinit var editText:EditText
    lateinit var editText2:EditText
    lateinit var btn:Button
    lateinit var btn1:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //we connect the variables to their id
        editText = findViewById(R.id.Email)
        editText2 = findViewById(R.id.password)
        btn = findViewById(R.id.regester)
        btn1 = findViewById(R.id.signin)


        //we make the function when the user press the button
        btn.setOnClickListener {
            //variables of email and password
            val email = editText.text.toString().trim()
            val pass = editText2.text.toString().trim()
            if (email.isEmpty().or(pass.isEmpty())){
                //toast to show that if something wrong
                Toast.makeText(this,"please enter email",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            signUp(email,pass)
        }
        //we do the same but another button for sign in
        btn1.setOnClickListener {
            val email = editText.text.toString().trim()
            val pass = editText2.text.toString().trim()


            signIn(email,pass)
        }

    }
    //function of sign in we pur parameters of email and password
    private fun signIn(email:String,password: String) {
        Toast.makeText(this,"signing in ... ",Toast.LENGTH_LONG).show()
        myAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, OnCompleteListener { task ->
            if (task.isSuccessful) {
                var intent = Intent(this, Users::class.java)
                intent.putExtra("id", myAuth.currentUser?.email)
                startActivity(intent)
            } else {
                Toast.makeText(
                    this, "somethig went wrong" + task.exception?.message
                    , Toast.LENGTH_LONG
                ).show()
            }

            })
    }
    // function to sign up and save the data in database
    private fun signUp(email: String, password: String) {

                myAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, OnCompleteListener { task ->
                        if (task.isSuccessful) {


                            val myDatabase:DatabaseReference = FirebaseDatabase.getInstance().getReference("users")
                            val usersID:String = myDatabase.push().key.toString()
                            val user = Users1(email,1f, 1f)
                            myDatabase.child(usersID).setValue(user).addOnCompleteListener {
                                Toast.makeText(this,"saved",Toast.LENGTH_LONG).show()
                            }

                            Toast.makeText(this, "done successfully !", Toast.LENGTH_LONG).show()


                        } else {
                            Toast.makeText(
                                this, "something went wrong" + task.exception?.message
                                , Toast.LENGTH_LONG
                            ).show()
                        }
                    })
            }




    }




