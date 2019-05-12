package com.example.firebase

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView



class userLocationAdapter (var mCtx: Context, var resorce:Int, var item: MutableList<uls>)
    : ArrayAdapter<uls>(mCtx,resorce,item){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(resorce,null)
        val textView3: TextView = view.findViewById(R.id.lateview)

        val user: uls = item[position]

        textView3.text = user.time








        return view
    }
}