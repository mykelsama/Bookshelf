package com.example.bookshelf.fake

import com.example.bookshelf.model.*

object FakeDataSource {
    val searchResponse = BookSearchResponse(
        items = listOf(BookItem("A"), BookItem("B"), BookItem("C"))
    )

    val details = mapOf(
        "A" to BookDetail("A", VolumeInfo("Libro A", ImageLinks("http://books.google.com/a"))),
        "B" to BookDetail("B", VolumeInfo("Libro B", ImageLinks("http://books.google.com/b"))),
        "C" to BookDetail("C", VolumeInfo("Libro C", null)) // sin imagen -> se descarta
    )

    val expectedBooks = listOf(
        Book("A", "Libro A", "https://books.google.com/a"),
        Book("B", "Libro B", "https://books.google.com/b")
    )
}