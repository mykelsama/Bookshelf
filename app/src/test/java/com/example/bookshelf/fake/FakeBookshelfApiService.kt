package com.example.bookshelf.fake

import com.example.bookshelf.model.BookDetail
import com.example.bookshelf.model.BookSearchResponse
import com.example.bookshelf.network.BookshelfApiService

class FakeBookshelfApiService : BookshelfApiService {
    override suspend fun searchBooks(query: String, key: String?): BookSearchResponse =
        FakeDataSource.searchResponse

    override suspend fun getBook(id: String, key: String?): BookDetail =
        FakeDataSource.details.getValue(id)
}