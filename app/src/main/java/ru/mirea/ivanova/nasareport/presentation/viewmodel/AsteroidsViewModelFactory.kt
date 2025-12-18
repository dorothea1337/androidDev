package ru.mirea.ivanova.nasareport.presentation.viewmodel

import androidx.lifecycle.ViewModel
import ru.mirea.ivanova.nasareport.domain.usecases.GetAsteroidsUseCase

class AsteroidsViewModelFactory(
    private val useCase: GetAsteroidsUseCase
) : androidx.lifecycle.ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AsteroidsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AsteroidsViewModel(useCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
