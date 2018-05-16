package com.example.jay.androidchallenge.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.AdapterView
import com.example.jay.androidchallenge.R
import com.example.jay.androidchallenge.adapters.ProductsAdapter
import com.example.jay.androidchallenge.models.Product
import com.example.jay.androidchallenge.models.ProductModel
import com.example.jay.androidchallenge.retrofit.APIs
import com.example.jay.androidchallenge.retrofit.RetrofitController
import com.example.jay.androidchallenge.utils.hideSoftKeyboard
import com.example.jay.androidchallenge.utils.isConnectedToInternet
import com.example.jay.androidchallenge.utils.toast
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var productsAdapter: ProductsAdapter
    private lateinit var searchAdapter: SearchAdapter
    private var listOfProducts: ArrayList<Product>? = arrayListOf()
    private var listOfProductsFiltered: ArrayList<Product>? = arrayListOf()
    private var isFabOpen: Boolean = false
    private var filterSale: Boolean = false
    private lateinit var fabOpenAnimation: Animation
    private lateinit var fabCloseAnimation: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fabOpenAnimation = AnimationUtils.loadAnimation(applicationContext, R.anim.fab_open)
        fabCloseAnimation = AnimationUtils.loadAnimation(applicationContext, R.anim.fab_close)

        floating_action_button_on_sale.startAnimation(fabCloseAnimation)

        productsAdapter = ProductsAdapter(this, {

            hideSoftKeyboard(edt_product_list_search)

            val intent = Intent(applicationContext, ProductDetailsActivity::class.java).apply {
                putExtra("details", it)
            }
            startActivity(intent)
        })

        rv_products.layoutManager = LinearLayoutManager(this)
        rv_products.adapter = productsAdapter

        searchAdapter = SearchAdapter(this)
        edt_product_list_search.setAdapter(searchAdapter)

        edt_product_list_search.threshold = 1

        getProductsList()

        edt_product_list_search.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->

            val product = searchAdapter.getItem(position)

            val intent = Intent(applicationContext, ProductDetailsActivity::class.java).apply {
                putExtra("details", product)
            }
            startActivity(intent)
        }

        floating_action_button_filter.setOnClickListener {
            animateFAB()
        }

        floating_action_button_on_sale.setOnClickListener {

            animateFAB()

            if (filterSale) {
                productsAdapter.updateValues(listOfProducts)
                searchAdapter.updateValues(listOfProducts)
            } else {
                listOfProductsFiltered?.clear()
                listOfProducts?.forEach {
                    if (it.isOnSale) {
                        listOfProductsFiltered?.add(it)
                    }
                }

                productsAdapter.updateValues(listOfProductsFiltered)
                searchAdapter.updateValues(listOfProductsFiltered)
            }
            filterSale = !filterSale
        }
    }

    private fun animateFAB() {

        if (isFabOpen) {
            floating_action_button_on_sale.startAnimation(fabCloseAnimation)
            floating_action_button_on_sale.isClickable = false
            isFabOpen = false
        } else {
            floating_action_button_on_sale.startAnimation(fabOpenAnimation)
            floating_action_button_on_sale.isClickable = true
            isFabOpen = true
        }

    }

    private fun getProductsList() {

        if (isConnectedToInternet()) {

            val api: APIs = RetrofitController.instance.create(APIs::class.java)

            val call: Call<ProductModel> = api.getProductDetails()
            call.enqueue(object : Callback<ProductModel> {
                override fun onResponse(call: Call<ProductModel>, response: Response<ProductModel>) {

                    progress_bar_list_products.visibility = View.GONE

                    listOfProducts = response.body()?.products

                    productsAdapter.updateValues(listOfProducts)
                    searchAdapter.updateValues(listOfProducts)

                    if (productsAdapter.itemCount > 0) {

                        rv_products.visibility = View.VISIBLE
                        edt_product_list_search.visibility = View.VISIBLE
                        txt_product_list_message.visibility = View.GONE

                    } else {
                        rv_products.visibility = View.GONE
                        edt_product_list_search.visibility = View.GONE
                        txt_product_list_message.visibility = View.VISIBLE
                        txt_product_list_message.setText(R.string.products_not_available_please_try_again_later)
                    }
                }

                override fun onFailure(call: Call<ProductModel>, t: Throwable) {
                    rv_products.visibility = View.GONE
                    edt_product_list_search.visibility = View.GONE
                    txt_product_list_message.visibility = View.VISIBLE
                    txt_product_list_message.text = t.message
                }
            })

            progress_bar_list_products.visibility = View.VISIBLE
            rv_products.visibility = View.GONE
            txt_product_list_message.visibility = View.GONE
            edt_product_list_search.visibility = View.GONE

        } else {
            txt_product_list_message.setText(R.string.please_check_your_internet_connection)
            toast(R.string.please_check_your_internet_connection)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if (item?.itemId == R.id.sort_by_discount_less_to_more) {



            return true
        } else if (item?.itemId == R.id.sort_by_discount_more_to_less) {

            return true
        } else if (item?.itemId == R.id.sort_by_price_less_to_more) {

            return true
        } else if (item?.itemId == R.id.sort_by_price_more_to_less) {

            return true
        }

        return false
    }
}
