package com.scout.crudjpcapp.model.di

import com.scout.crudjpcapp.model.repository.ContactRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // ViewModels
    /*
    viewModel { AuthViewModel(get()) }
    viewModel { EventViewModel(get(), get()) }
    viewModel { ParticipantViewModel(get()) }
    viewModel { RsvpViewModel(get()) }
    viewModel { NetworkStatusViewModel(get()) }
    viewModel { SyncViewModel(get(), get()) }
    */

    // Repository
    single { ContactRepository(get()) }

    // Database and DAOs
    single { provideDatabase(get()) }
    single { provideContactDao(get()) }
}