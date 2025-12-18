package ru.mirea.ivanova.nasareport.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.mirea.ivanova.nasareport.domain.models.Asteroid
import ru.mirea.ivanova.nasareport.domain.usecases.GetAsteroidsUseCase
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class AsteroidsViewModel(
    private val getAsteroidsUseCase: GetAsteroidsUseCase
) : ViewModel() {

    val asteroids: LiveData<List<Asteroid>> = getAsteroidsUseCase.observeAsteroids()

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            getAsteroidsUseCase.refresh()
        }
    }
}