package com.example.amphibians.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.amphibians.R
import com.example.amphibians.ui.screens.AmViewModel
import com.example.amphibians.ui.screens.HomeScreen

@Composable
fun AmphibianApp(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { TopAppBar(
            title = { Text(text = stringResource(id = R.string.app_name))}
        )}
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            color = MaterialTheme.colors.background
        ) {
            val viewModel: AmViewModel =
                viewModel(factory = AmViewModel.Factory)
            HomeScreen(amUiState = viewModel.amUiState)
        }
    }
}