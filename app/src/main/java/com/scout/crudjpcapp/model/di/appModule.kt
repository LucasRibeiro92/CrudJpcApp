package com.scout.crudjpcapp.model.di

import com.scout.crudjpcapp.model.repository.ContactRepository
import com.scout.crudjpcapp.viewmodel.ContactViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // ViewModels
    viewModel { ContactViewModel(get()) }

    // Repository
    single { ContactRepository(get()) }

    // Database and DAOs
    single { provideDatabase(get()) }
    single { provideContactDao(get()) }
}