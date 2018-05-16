package com.example.jay.androidchallenge.utils

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.view.View
import android.widget.Toast
import android.content.Context.INPUT_METHOD_SERVICE
import android.view.inputmethod.InputMethodManager


fun Activity.isConnectedToInternet(): Boolean {
    val connectivity: ConnectivityManager = getSystemService(Activity.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo: NetworkInfo = connectivity.activeNetworkInfo as NetworkInfo
    return networkInfo.isConnected
}

fun Activity.toast(message: Int) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Activity.hideSoftKeyboard(view: View){
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}