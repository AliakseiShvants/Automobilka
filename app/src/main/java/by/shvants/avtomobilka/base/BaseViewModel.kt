package by.shvants.avtomobilka.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.shvants.avtomobilka.presentation.state.VisibilityState
import org.koin.core.component.KoinComponent

abstract class BaseViewModel : ViewModel() {

    private val _progressState = MutableLiveData<VisibilityState>(VisibilityState.Show)

    val progressState: LiveData<VisibilityState>
        get() = _progressState

    fun hideLoading() {
        _progressState.value = VisibilityState.Hide
    }
}