package ru.mirea.ivanova.nasareport.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.mirea.ivanova.nasareport.domain.usecases.GetApodUseCase

class ApodViewModelFactory(
    private val getApodUseCase: GetApodUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ApodViewModel::class.java)) {
            return ApodViewModel(getApodUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
