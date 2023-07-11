package by.shvants.avtomobilka.presentation.state

sealed class VisibilityState {
    object Hide : VisibilityState()
    object Show : VisibilityState()
}