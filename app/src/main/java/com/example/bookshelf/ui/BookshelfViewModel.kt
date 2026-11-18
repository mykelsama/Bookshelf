package com.example.bookshelf.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bookshelf.BookshelfApplication
import com.example.bookshelf.data.BooksRepository
import com.example.bookshelf.model.Book
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface BookshelfUiState {
    data class Success(val books: List<Book>) : BookshelfUiState
    data class Error(val message: String) : BookshelfUiState
    object Loading : BookshelfUiState
}

class BookshelfViewModel(
    private val booksRepository: BooksRepository
) : ViewModel() {

    var uiState: BookshelfUiState by mutableStateOf(BookshelfUiState.Loading)
        private set

    var query: String by mutableStateOf("jazz history")
        private set

    init {
        getBooks()
    }

    fun onQueryChange(newQuery: String) {
        query = newQuery
    }

    fun getBooks() {
        val search = query.trim()
        if (search.isEmpty()) return
        viewModelScope.launch {
            uiState = BookshelfUiState.Loading
            uiState = try {
                BookshelfUiState.Success(booksRepository.getBooks(search))
            } catch (e: IOException) {
                Log.e("Bookshelf", "IOException", e)
                BookshelfUiState.Error("Sin conexión: ${e.javaClass.simpleName}")
            } catch (e: HttpException) {
                Log.e("Bookshelf", "HTTP ${e.code()}", e)
                BookshelfUiState.Error("Error HTTP ${e.code()}")
            } catch (e: Exception) {
                Log.e("Bookshelf", "Error", e)
                BookshelfUiState.Error("${e.javaClass.simpleName}: ${e.message}")
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BookshelfApplication)
                BookshelfViewModel(application.container.booksRepository)
            }
        }
    }
}