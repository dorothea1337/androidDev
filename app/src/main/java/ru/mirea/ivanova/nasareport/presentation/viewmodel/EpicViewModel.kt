package ru.mirea.ivanova.nasareport.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.mirea.ivanova.nasareport.domain.models.EpicImage
import ru.mirea.ivanova.nasareport.domain.usecases.GetEpicUseCase

class EpicViewModel(private val getEpicUseCase: GetEpicUseCase) : ViewModel() {

    val epicList: LiveData<List<EpicImage>> = getEpicUseCase.observeEpicList()
    val epicNetwork: LiveData<List<EpicImage>> = getEpicUseCase.observeNetworkEpic()

    fun refresh() {
        viewModelScope.launch { getEpicUseCase.refresh() }
    }
}
