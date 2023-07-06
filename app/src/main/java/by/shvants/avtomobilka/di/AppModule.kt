package by.shvants.avtomobilka.di

import by.shvants.avtomobilka.repository.CarRepository
import org.koin.dsl.module

val appModule = module {
    single { CarRepository(get()) }
}