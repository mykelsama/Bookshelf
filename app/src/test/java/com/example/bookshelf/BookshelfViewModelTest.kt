package com.example.bookshelf

import com.example.bookshelf.fake.FakeBooksRepository
import com.example.bookshelf.fake.FakeDataSource
import com.example.bookshelf.rules.TestDispatcherRule
import com.example.bookshelf.ui.BookshelfUiState
import com.example.bookshelf.ui.BookshelfViewModel
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class BookshelfViewModelTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun bookshelfViewModel_getBooks_verifyUiStateSuccess() {
        val viewModel = BookshelfViewModel(booksRepository = FakeBooksRepository())
        assertEquals(
            BookshelfUiState.Success(FakeDataSource.expectedBooks),
            viewModel.uiState
        )
    }
}