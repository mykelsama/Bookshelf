package com.example.bookshelf.data

import com.example.bookshelf.model.Book
import com.example.bookshelf.network.BookshelfApiService

interface BooksRepository {
    suspend fun getBooks(query: String): List<Book>
}

class NetworkBooksRepository(
    private val bookshelfApiService: BookshelfApiService
) : BooksRepository {

    override suspend fun getBooks(query: String): List<Book> {
        // 1) Búsqueda -> ids
        val ids = bookshelfApiService.searchBooks(query).items
            ?.map { it.id }
            ?.distinct()
            ?: emptyList()

        // 2) Una petición por libro, una tras otra en la misma corrutina
        val books = mutableListOf<Book>()
        for (id in ids) {
            val detail = bookshelfApiService.getBook(id)
            val thumbnail = detail.volumeInfo.imageLinks?.thumbnail ?: continue
            books.add(
                Book(
                    id = detail.id,
                    title = detail.volumeInfo.title ?: "Sin título",
                    thumbnailUrl = thumbnail.replace("http://", "https://")
                )
            )
        }
        return books
    }
}