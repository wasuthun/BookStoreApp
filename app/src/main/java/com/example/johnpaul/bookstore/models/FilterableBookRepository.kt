package com.example.johnpaul.bookstore.models

abstract class FilterableBookRepository : BookRepository() {

    protected val bookList: ArrayList<Book> = ArrayList()

    override fun getBooks(): ArrayList<Book> {
        return bookList
    }

    fun filter(keyword: String): List<Book> {
        return bookList.filter({ book: Book ->
            book.title.contains(keyword, true) || book.publicationYear.toString().contains(keyword, true)
        })
    }
}