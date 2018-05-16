package com.example.jay.androidchallenge.models

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(@SerializedName("name") @Expose var name: String?,
                   @SerializedName("style") @Expose var style: String?,
                   @SerializedName("code_color") @Expose var codeColor: String?,
                   @SerializedName("color_slug")  @Expose var colorSlug: String?,
                   @SerializedName("color") @Expose var color: String?,
                   @SerializedName("on_sale") @Expose var isOnSale: Boolean,
                   @SerializedName("regular_price")  @Expose var regularPrice: String?,
                   @SerializedName("actual_price") @Expose var actualPrice: String?,
                   @SerializedName("discount_percentage") @Expose var discountPercentage: String?,
                   @SerializedName("installments") @Expose var installments: String?,
                   @SerializedName("image") @Expose var image: String?,
                   @SerializedName("sizes") @Expose var sizes: ArrayList<Size>) : Parcelable