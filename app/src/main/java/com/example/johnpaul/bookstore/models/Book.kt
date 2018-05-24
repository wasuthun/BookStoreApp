package com.example.johnpaul.bookstore.models

class Book(
        val id: Int,
        val title: String,
        val price: Double = 0.0,
        val publicationYear: Int = 0,
        val imageURL: String = ""
) {

    override fun toString(): String {
        return "${title} (${price})"
    }

    override fun equals(other: Any?): Boolean {
        if (other == null) return false
        if (this::class != other::class) return false
        val book = other as Book
        return this.id == book.id &&
                this.title.equals(book.title) &&
                this.price == book.price &&
                this.publicationYear == book.publicationYear &&
                this.imageURL.equals(book.imageURL)
    }
}