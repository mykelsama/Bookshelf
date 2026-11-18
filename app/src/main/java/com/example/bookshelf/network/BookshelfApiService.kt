package com.example.bookshelf.network

import com.example.bookshelf.model.BookDetail
import com.example.bookshelf.model.BookSearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BookshelfApiService {

    @GET("volumes")
    suspend fun searchBooks(
        @Query("q") query: String,
        @Query("key") key: String? = API_KEY.ifBlank { null }
    ): BookSearchResponse

    @GET("volumes/{id}")
    suspend fun getBook(
        @Path("id") id: String,
        @Query("key") key: String? = API_KEY.ifBlank { null }
    ): BookDetail

    companion object {
        // Si te sale error 429, pega aquí tu API key de Google Books
        const val API_KEY = "AIzaSyBrEDG0BFbZeFL6f7k1VJkP8H3GdsGVTis"
    }
}