package ru.mirea.ivanova.nasareport.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.mirea.ivanova.nasareport.domain.models.Asteroid
import ru.mirea.ivanova.nasareport.domain.usecases.GetAsteroidsUseCase

class AsteroidPreviewViewModel(
    private val getAsteroidsUseCase: GetAsteroidsUseCase
) : ViewModel() {

    private val asteroidsLiveData = getAsteroidsUseCase.observeAsteroids()

    private val _top3Asteroids = MediatorLiveData<List<Asteroid>>()
    val top3Asteroids: LiveData<List<Asteroid>> = _top3Asteroids

    init {
        _top3Asteroids.addSource(asteroidsLiveData) { list ->
            _top3Asteroids.value = list.take(3)
        }
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            getAsteroidsUseCase.refresh()
        }
    }
}