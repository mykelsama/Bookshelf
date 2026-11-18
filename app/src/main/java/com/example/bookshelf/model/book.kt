package com.example.bookshelf.model

// ---- DTOs (JSON de Google Books, parseado con Gson) ----
data class BookSearchResponse(
    val items: List<BookItem>? = null
)

data class BookItem(
    val id: String
)

data class BookDetail(
    val id: String,
    val volumeInfo: VolumeInfo
)

data class VolumeInfo(
    val title: String? = null,
    val imageLinks: ImageLinks? = null
)

data class ImageLinks(
    val thumbnail: String? = null
)

// ---- Modelo que usa la UI ----
data class Book(
    val id: String,
    val title: String,
    val thumbnailUrl: String
)