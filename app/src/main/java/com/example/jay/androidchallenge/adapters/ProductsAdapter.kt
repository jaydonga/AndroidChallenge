package com.example.jay.androidchallenge.adapters

import android.content.Context
import android.graphics.Paint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.jay.androidchallenge.models.ProductModel
import com.example.jay.androidchallenge.R
import com.example.jay.androidchallenge.models.Product
import kotlinx.android.synthetic.main.row_product_information.*
import kotlinx.android.synthetic.main.row_product_information.view.*

class ProductsAdapter(private val context: Context, private val listener: (Product) -> Unit) : RecyclerView.Adapter<ProductsAdapter.ProductHolder>() {

    private var listProducts: ArrayList<Product>? = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ProductHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_product_information, parent, false))

    override fun getItemCount(): Int {
        return listProducts!!.size
    }

    override fun onBindViewHolder(productHolder: ProductHolder, position: Int) {
        productHolder.bind(listProducts!![position], listener)
    }

    inner class ProductHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(model: Product, listener: (Product) -> Unit) = with(itemView) {

            Glide.with(context)
                    .load(model.image)
                    .thumbnail(0.1f)
                    .error(R.drawable.default_product_image)
                    .into(iv_row_products_image)

            txt_row_product_name.text = model.name
            txt_row_product_price.text = model.regularPrice

            if(model.regularPrice.equals(model.actualPrice)){

                txt_row_product_price.paintFlags = Paint.ANTI_ALIAS_FLAG

                txt_row_product_actual_price.visibility = View.INVISIBLE

            }else{

                txt_row_product_price.paintFlags = txt_row_product_price.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

                txt_row_product_actual_price.visibility = View.VISIBLE
                txt_row_product_actual_price.text = model.actualPrice
            }



            itemView.setOnClickListener { listener.invoke(model) }
        }
    }

    fun updateValues(listProducts: ArrayList<Product>?){
        if(listProducts != null) {
            this.listProducts = listProducts
        }
        notifyDataSetChanged()
    }
}