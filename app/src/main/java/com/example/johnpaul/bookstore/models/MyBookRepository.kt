package com.example.johnpaul.bookstore.models


class MyBookRepository : FilterableBookRepository() {

    override fun loadAllBooks() {
        bookList.clear()
        bookList.add(Book(43, "Ship It!", 20.0))
        bookList.add(Book(49, "Programming Ruby", 25.0))
        bookList.add(Book(59, "The Pragmatic Programmer", 32.0))
        setChanged()
        notifyObservers()
    }

    fun addBook(book: Book): Boolean {
        if (!bookList.contains(book)) {
            bookList.add(book)
            setChanged()
            notifyObservers()
            return true
        }
        return false
    }

}