package by.shvants.avtomobilka.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import by.shvants.avtomobilka.base.BaseViewModel
import by.shvants.avtomobilka.domain.Car
import by.shvants.avtomobilka.data.repository.CarRepositoryImpl
import by.shvants.avtomobilka.domain.CarRepository
import by.shvants.avtomobilka.utils.RequestResult
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CarsViewModel : BaseViewModel(), KoinComponent {

    private val carsRepository: CarRepository by inject()

    private val _carsList = MutableLiveData<List<Car>?>(null)
    private val _scrollPosition = MutableLiveData<Int>(0)

    val carsList: LiveData<List<Car>?>
        get() = _carsList
    val scrollPosition: LiveData<Int>
        get() = _scrollPosition

    init {
        fetchCarsFromRemote()
    }

    fun fetchCarsFromRemote() {
        viewModelScope.launch {
            when(val result = carsRepository.fetchCarsFromServer()) {
                is RequestResult.Success -> {
                    hideLoading()
                    _carsList.value = result.value as List<Car>

                }
                is RequestResult.Error -> {
                    hideLoading()
                    _carsList.value = emptyList()
                }
            }
        }
    }

    fun setScrollPosition(position: Int) {
        _scrollPosition.value = position
    }
}