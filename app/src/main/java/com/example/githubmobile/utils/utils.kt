package com.example.githubmobile.utils

import android.app.Activity
import android.util.Log
import android.view.View
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

fun Activity.showToast(message: String){
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

}

fun log(message:String){
    Log.d("TAGG", message)
}

fun View.visible(){
    this.visibility = View.VISIBLE
}


fun View.gone(){
    this.visibility= View.GONE
}

 fun String.getDate(): String {
    val dateString = this.replace("[TZ]".toRegex(), " ")
    val date = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault()).parse(dateString)
    val simpleDateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
    date?.let {
        return simpleDateFormat.format(date)
    }
    return "no date"
}