package com.example.jay.androidchallenge.retrofit

import com.example.jay.androidchallenge.models.ProductModel
import retrofit2.Call
import retrofit2.http.GET

interface APIs {

    @GET("59b6a65a0f0000e90471257d")
    fun getProductDetails(): Call<ProductModel>

}