package com.example.composeapplication.compose.di


import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.composeapplication.compose.MainViewModel
import com.example.composeapplication.compose.data.ConferenceRepository
import com.example.composeapplication.compose.data.ConferenceRepositoryImpl
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module{

    single<DataStore<Preferences>> {
        PreferenceDataStoreFactory.create(
            produceFile = {
                androidContext().preferencesDataStoreFile("favorite_ids")
            }
        )
    }
    single<CoroutineDispatcher> { Dispatchers.IO }

    single { ConferenceRepositoryImpl(get(), get(), get()) } bind ConferenceRepository::class
    viewModel { MainViewModel(get(), get()) }

}