package by.shvants.avtomobilka.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import by.shvants.avtomobilka.base.BaseViewModel
import by.shvants.avtomobilka.data.Car
import by.shvants.avtomobilka.repository.CarRepository
import by.shvants.avtomobilka.utils.RequestResult
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CarsViewModel : BaseViewModel(), KoinComponent {

    private val carsRepository: CarRepository by inject()

    private val _carsList = MutableLiveData<List<Car>?>(null)

    val carsList: LiveData<List<Car>?>
        get() = _carsList

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
}