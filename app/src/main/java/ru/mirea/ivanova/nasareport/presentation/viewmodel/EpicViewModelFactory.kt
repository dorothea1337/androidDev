package ru.mirea.ivanova.nasareport.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.mirea.ivanova.nasareport.domain.usecases.GetEpicUseCase

class EpicViewModelFactory(private val useCase: GetEpicUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EpicViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EpicViewModel(useCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
