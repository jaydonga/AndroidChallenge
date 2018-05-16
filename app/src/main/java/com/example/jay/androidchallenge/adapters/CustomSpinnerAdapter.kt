package com.example.jay.androidchallenge.adapters

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.SpinnerAdapter
import android.widget.TextView
import com.example.jay.androidchallenge.R
import com.example.jay.androidchallenge.models.ProductModel
import com.example.jay.androidchallenge.models.Size

class CustomSpinnerAdapter(context: Context, private val listColors: ArrayList<Size>) : BaseAdapter(), SpinnerAdapter {

    private val blackColor: Int = ContextCompat.getColor(context, R.color.black_color)
    private val greyColor: Int = ContextCompat.getColor(context, R.color.row_bg_color)

    override fun getCount(): Int {
        return listColors.size
    }

    override fun getItem(position: Int): Any {
        return listColors[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, _convertView: View?, parent: ViewGroup): View? {

        val holder: ViewHolder
        var convertView: View? = _convertView

        if (convertView == null) {

            convertView = LayoutInflater.from(parent.context).inflate(R.layout.row_spinner_item, parent, false)

            holder = ViewHolder()
            holder.txtColorName = convertView.findViewById(R.id.txt_row_spinner_item_text)

            convertView.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
        }

        holder.txtColorName.text = listColors[position].size
        holder.txtColorName.setTextColor(blackColor)

        return convertView
    }

    inner class ViewHolder {
        lateinit var txtColorName: TextView
    }

    override fun getDropDownView(position: Int, _convertView: View?, parent: ViewGroup): View? {
        val holder: ViewHolder
        var convertView: View? = _convertView

        if (convertView == null) {

            convertView = LayoutInflater.from(parent.context).inflate(R.layout.row_spinner_dropdown_item, parent, false)

            holder = ViewHolder()
            holder.txtColorName = convertView.findViewById(R.id.txt_row_spinner_dropdown_item_text)

            convertView.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
        }

        holder.txtColorName.text = listColors[position].size

        if (listColors[position].isAvailable) {
            holder.txtColorName.setTextColor(blackColor)
        } else {
            holder.txtColorName.setTextColor(greyColor)
        }

        return convertView
    }
}