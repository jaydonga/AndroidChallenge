package com.example.jay.androidchallenge.models

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Size(@SerializedName("available") @Expose var isAvailable: Boolean,
                @SerializedName("size") @Expose var size: String?,
                @SerializedName("sku") @Expose var sku: String?) : Parcelable