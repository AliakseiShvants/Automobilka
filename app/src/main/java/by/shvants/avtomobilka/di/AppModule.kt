package by.shvants.avtomobilka.di

import by.shvants.avtomobilka.data.mapper.*
import by.shvants.avtomobilka.data.repository.CarRepositoryImpl
import by.shvants.avtomobilka.data.repository.PostRepositoryImpl
import by.shvants.avtomobilka.domain.CarRepository
import by.shvants.avtomobilka.domain.PostRepository
import by.shvants.avtomobilka.presentation.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<ImageToDomainMapper> { ImageToDomainMapper() }
    single<CarToDomainMapper> { CarToDomainMapper(get()) }
    single<UserToDomainMapper> { UserToDomainMapper() }
    single<PostToDomainMapper> { PostToDomainMapper(get()) }
    single<PostsToDomainMapper> { PostsToDomainMapper(get(), get()) }

    single<CarRepository> {
        CarRepositoryImpl(
            carApi = get(),
            carToDomainMapper = get(),
        )
    }
    factory<PostRepository> {
        PostRepositoryImpl(
            carApi = get(),
            postsToDomainMapper = get()
        )
    }

    scope<CarsFragment> {
        viewModel {
            CarsViewModel()
        }
    }
    scope<CarDetailsFragment> {
        viewModel {
            CarDetailsViewModel()
        }
    }
}