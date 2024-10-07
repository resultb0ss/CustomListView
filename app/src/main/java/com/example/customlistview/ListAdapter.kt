package com.example.customlistview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

class ListAdapter (context: Context, personList: MutableList<Person>):
    ArrayAdapter<Person>(context,R.layout.list_item, personList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        val person = getItem(position)
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        }

        val imageViewIV = view?.findViewById<ImageView>(R.id.itemListViewImageIV)
        val personNameTV = view?.findViewById<TextView>(R.id.itemListViewNameTV)
        val personAgeTV = view?.findViewById<TextView>(R.id.itemListViewAgeTV)
        val personPhoneTV = view?.findViewById<TextView>(R.id.itemListViewNumberTV)

        imageViewIV?.setImageBitmap(person?.image)
        personNameTV?.text = person?.name
        personAgeTV?.text = person?.age
        personPhoneTV?.text = person?.phone


        return super.getView(position, convertView, parent)
    }

}