package by.shvants.avtomobilka.di

import by.shvants.avtomobilka.data.CarToDomainMapper
import by.shvants.avtomobilka.presentation.CarsFragment
import by.shvants.avtomobilka.presentation.CarsViewModel
import by.shvants.avtomobilka.repository.CarRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<CarToDomainMapper> { CarToDomainMapper() }
    single<CarRepository> { CarRepository(carApi = get(), carToDomainMapper = get()) }

    scope<CarsFragment> {
        viewModel {
            CarsViewModel()
        }
    }
}