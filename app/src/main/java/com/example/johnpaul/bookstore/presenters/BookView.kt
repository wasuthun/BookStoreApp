package com.example.johnpaul.bookstore.presenters

import com.example.johnpaul.bookstore.models.Book

interface BookView {
    fun setBookList(books: List<Book>)
}