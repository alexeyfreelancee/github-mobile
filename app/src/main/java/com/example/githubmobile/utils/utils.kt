package com.example.githubmobile.utils

import android.app.Activity
import android.util.Log
import android.view.View
import android.widget.Toast

fun Activity.showToast(message: String){
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

}

fun log(message:String){
    Log.d("tagg", message)
}

fun View.visible(){
    this.visibility = View.VISIBLE
}


fun View.gone(){
    this.visibility= View.GONE
}