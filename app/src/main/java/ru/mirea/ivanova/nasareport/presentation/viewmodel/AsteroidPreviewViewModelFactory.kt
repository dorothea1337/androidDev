package ru.mirea.ivanova.nasareport.presentation.viewmodel

import ru.mirea.ivanova.nasareport.domain.usecases.GetAsteroidsUseCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AsteroidPreviewViewModelFactory(
    private val useCase: GetAsteroidsUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AsteroidPreviewViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AsteroidPreviewViewModel(useCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
