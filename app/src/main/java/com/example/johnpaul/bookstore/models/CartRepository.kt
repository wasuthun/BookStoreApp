package com.example.johnpaul.bookstore.models

class CartRepository : FilterableBookRepository() {

    override fun loadAllBooks() {
        bookList.clear()
        setChanged()
        notifyObservers()
    }

    fun checkout() {
        
    }

}