package by.shvants.avtomobilka.presentation

import android.util.Log
import androidx.lifecycle.viewModelScope
import by.shvants.avtomobilka.base.BaseViewModel
import by.shvants.avtomobilka.repository.CarRepository
import by.shvants.avtomobilka.utils.RequestResult
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CarsViewModel : BaseViewModel(), KoinComponent {

    private val carsRepository: CarRepository by inject()

    init {
        fetchCarsFromRemote()
    }

    private fun fetchCarsFromRemote() {
        viewModelScope.launch {
            when(val result = carsRepository.fetchCarsFromServer()) {
                is RequestResult.Success -> {
                    Log.d("CarsViewModel", result.value.toString())
                    hideLoading()

                }
                is RequestResult.Error -> {
                    Log.d("CarsViewModel", result.exception.toString())
                    hideLoading()
                }
            }
        }
    }
}