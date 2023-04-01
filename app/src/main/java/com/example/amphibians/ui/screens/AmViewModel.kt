package com.example.amphibians.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.amphibians.AmApplication
import com.example.amphibians.data.AmRepository
import com.example.amphibians.model.AmphibianArticle
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface AmUiState{
    data class Success(val articles: List<AmphibianArticle>): AmUiState
    object Loading: AmUiState
    object Error: AmUiState
}

class AmViewModel(
    private val amRepository: AmRepository
): ViewModel() {
    var amUiState: AmUiState by mutableStateOf(AmUiState.Loading)
        private set

    init {
        getAmArticles()
    }

    private fun getAmArticles(){
        viewModelScope.launch {
            amUiState = try {
                AmUiState.Success(amRepository.getAmArticles())
            }catch (e: IOException){
                AmUiState.Error
            }catch (e: HttpException){
                AmUiState.Error
            }
        }
    }

    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as AmApplication)
                val amRepository = application.container.amRepository
                AmViewModel(amRepository = amRepository)
            }
        }
    }
}