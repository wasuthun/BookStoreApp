package com.example.johnpaul.bookstore.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.johnpaul.bookstore.models.Book


class TwoLineArrayAdapter(
        ctx: Context,
        val books: ArrayList<Book>
) : ArrayAdapter<Book>(
        ctx,
        android.R.layout.simple_list_item_2,
        android.R.id.text1,
        books
) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = super.getView(position, convertView, parent)
        val text1: TextView = view.findViewById(android.R.id.text1)
        val text2: TextView = view.findViewById(android.R.id.text2)
        val book: Book = books.get(position)
        text1.text = book.title
        text2.text = "$${book.price} USD"
        return view
    }
}