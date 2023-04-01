package com.example.amphibians.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.amphibians.model.AmphibianArticle

sealed interface AmUiState{
    data class Success(val articles: List<AmphibianArticle>): AmUiState
    object Loading: AmUiState
    object Error: AmUiState
}

class AmViewModel: ViewModel() {
    var amUiState: AmUiState by mutableStateOf(AmUiState.Loading)
        private set
}