package com.example.jay.androidchallenge.activities

import android.app.Activity
import android.graphics.Paint
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import com.bumptech.glide.Glide
import com.example.jay.androidchallenge.R
import com.example.jay.androidchallenge.adapters.CustomSpinnerAdapter
import com.example.jay.androidchallenge.models.Product
import kotlinx.android.synthetic.main.activity_product_details.*

class ProductDetailsActivity : Activity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)

        val product : Product = intent.getParcelableExtra("details")

        Glide
                .with(this)
                .load(product.image)
                .thumbnail(0.1f)
                .error(R.drawable.default_product_image)
                .into(img_product_details_image)

        txt_product_details_name.text = product.name
        txt_product_details_price.text = product.regularPrice

        if(product.regularPrice.equals(product.actualPrice)){
            txt_product_details_discounted_price.visibility = View.GONE
            txt_product_details_discount_percentage.visibility = View.GONE
        }else{

            txt_product_details_price.paintFlags = txt_product_details_price.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

            txt_product_details_discounted_price.visibility = View.VISIBLE
            txt_product_details_discount_percentage.visibility = View.VISIBLE
            txt_product_details_discounted_price.text = product.actualPrice
            txt_product_details_discount_percentage.text = getString(R.string.discount, product.discountPercentage)
        }

        if(product.isOnSale){
            txt_product_details_sale.setText(R.string.on_sale)
            txt_product_details_sale.setTextColor(ContextCompat.getColor(applicationContext, R.color.green_color))
        }else{
            txt_product_details_sale.setText(R.string.not_on_sale)
            txt_product_details_sale.setTextColor(ContextCompat.getColor(applicationContext, R.color.red_color))
        }

        spinner_available_sizes.adapter = CustomSpinnerAdapter(applicationContext, product.sizes)
    }
}