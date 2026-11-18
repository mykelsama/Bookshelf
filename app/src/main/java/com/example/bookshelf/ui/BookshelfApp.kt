package com.example.bookshelf.ui

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookshelf.ui.screens.HomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookshelfApp(modifier: Modifier = Modifier) {
    val viewModel: BookshelfViewModel = viewModel(factory = BookshelfViewModel.Factory)

    Scaffold(
        modifier = modifier,
        topBar = { CenterAlignedTopAppBar(title = { Text("Bookshelf") }) }
    ) { innerPadding ->
        HomeScreen(
            uiState = viewModel.uiState,
            query = viewModel.query,
            onQueryChange = viewModel::onQueryChange,
            onSearch = viewModel::getBooks,
            contentPadding = innerPadding
        )
    }
}