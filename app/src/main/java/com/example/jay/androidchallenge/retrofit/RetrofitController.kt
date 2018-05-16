package com.example.jay.androidchallenge.retrofit

import com.google.gson.GsonBuilder

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitController {

    private var retrofit: Retrofit? = null

    val instance: Retrofit
        get() {

            if (retrofit == null) {

                val gson = GsonBuilder()
                        .setLenient()
                        .create()

                retrofit = Retrofit.Builder()
                        .baseUrl("http://www.mocky.io/v2/")
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build()

            }
            return retrofit!!
        }

}
