package com.example.bookshelf

import com.example.bookshelf.data.NetworkBooksRepository
import com.example.bookshelf.fake.FakeBookshelfApiService
import com.example.bookshelf.fake.FakeDataSource
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class BooksRepositoryTest {

    @Test
    fun networkBooksRepository_getBooks_verifyBookList() = runTest {
        val repository = NetworkBooksRepository(
            bookshelfApiService = FakeBookshelfApiService()
        )
        assertEquals(FakeDataSource.expectedBooks, repository.getBooks("jazz"))
    }
}