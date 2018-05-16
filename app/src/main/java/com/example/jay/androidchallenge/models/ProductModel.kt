package com.example.jay.androidchallenge.models

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductModel(@SerializedName("products")  @Expose var products: ArrayList<Product>) : Parcelable
