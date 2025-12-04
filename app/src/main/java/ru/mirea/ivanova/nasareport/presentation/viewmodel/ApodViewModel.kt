package ru.mirea.ivanova.nasareport.presentation.viewmodel

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import ru.mirea.ivanova.nasareport.domain.models.Apod
import ru.mirea.ivanova.nasareport.domain.usecases.GetApodUseCase

class ApodViewModel(
    private val getApodUseCase: GetApodUseCase
) : ViewModel() {

    // LiveData из БД
    private val dbLiveData = getApodUseCase.observeApodList()

    // LiveData из сети
    private val networkLiveData = getApodUseCase.observeNetworkApod()

    // MediatorLiveData объединяет обе
    private val _mergedData = MediatorLiveData<List<Apod>>()
    val mergedData: LiveData<List<Apod>> = _mergedData

    init {
        // Добавляем источники
        _mergedData.addSource(dbLiveData) { dbList ->
            val networkItem = networkLiveData.value
            _mergedData.value = mergeData(dbList, networkItem)
        }

        _mergedData.addSource(networkLiveData) { networkItem ->
            val dbList = dbLiveData.value
            _mergedData.value = mergeData(dbList, networkItem)
        }
    }

    // Функция объединяет данные из сети и БД
    private fun mergeData(dbList: List<Apod>?, networkItem: Apod?): List<Apod> {
        val result = mutableListOf<Apod>()
        dbList?.let { result.addAll(it) }
        networkItem?.let {
            // Добавляем только если в списке ещё нет такой даты
            if (dbList?.none { it.date == networkItem.date } != false) {
                result.add(0, networkItem) // ставим сеть сверху
            }
        }
        return result
    }

    fun refresh() {
        viewModelScope.launch {
            getApodUseCase.refresh()
        }
    }
}
