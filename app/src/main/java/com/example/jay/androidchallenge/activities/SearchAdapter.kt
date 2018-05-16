package com.example.jay.androidchallenge.activities

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

import com.example.jay.androidchallenge.R
import com.example.jay.androidchallenge.R.id.img_product_details_image
import com.example.jay.androidchallenge.models.Product
import kotlinx.android.synthetic.main.activity_product_details.*
import java.util.*

class SearchAdapter(private val context: Context) : BaseAdapter(), Filterable {

    private var listAllProducts = ArrayList<Product>()
    private var listDataProducts = ArrayList<Product>()

//    private var listFilteredProducts = ArrayList<Product>()
    private var mFilter: ArrayFilter? = null

    override fun getCount(): Int {
        return listDataProducts.size
    }

    override fun getItem(position: Int): Product {
        return listDataProducts[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, _convertView: View?, parent: ViewGroup): View {
        var convertView = _convertView

        val holder: ViewHolder

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.row_product_search, parent, false)

            holder = ViewHolder()
            holder.imageView = convertView!!.findViewById(R.id.img_row_product_search_image)
            holder.textView = convertView.findViewById(R.id.txt_row_product_search_name)

            convertView.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
        }

        holder.textView.text = getItem(position).name
        Glide
                .with(context)
                .load(getItem(position).image)
                .thumbnail(0.1f)
                .error(R.drawable.default_product_image)
                .into(holder.imageView)

        return convertView
    }

    override fun getFilter(): Filter? {
        if (mFilter == null) {
            mFilter = ArrayFilter()
        }
        return mFilter
    }

    internal inner class ViewHolder {
        lateinit var imageView: ImageView
        lateinit var textView: TextView
    }

    private inner class ArrayFilter : Filter() {

        /*override fun convertResultToString(resultValue: Any): String? {
            return if(resultValue is Product) resultValue.name else ""
        }*/

        override fun performFiltering(prefix: CharSequence?): Filter.FilterResults {
            val results = Filter.FilterResults()

            if (prefix == null || prefix.isEmpty()) {
                results.count = listAllProducts.size
                results.values = listAllProducts
            } else {

                val listFilteredProducts = arrayListOf<Product>()

                val searchTerm = prefix.toString().toLowerCase(Locale.ENGLISH)

                for (p in listAllProducts) {
                    if (p.name != null && p.name!!.toLowerCase(Locale.ENGLISH).contains(searchTerm)) {
                        listFilteredProducts.add(p)
                    }
                }
                results.count = listFilteredProducts.size
                results.values = listFilteredProducts
            }

            return results
        }

        override fun publishResults(constraint: CharSequence?, results: Filter.FilterResults) {

            listDataProducts = if (results.values != null) {
                results.values as ArrayList<Product>
            } else {
                listAllProducts
            }
            notifyDataSetChanged()
        }
    }

    fun updateValues(listAllProducts: ArrayList<Product>?){
        if(listAllProducts != null) {
            this.listAllProducts = listAllProducts
        }
        notifyDataSetChanged()
    }
}
