package com.example.githubmobile

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

fun View.makeVisible(){
    this.visibility = View.VISIBLE
}


fun View.makeInvisible(){
    this.visibility= View.GONE
}