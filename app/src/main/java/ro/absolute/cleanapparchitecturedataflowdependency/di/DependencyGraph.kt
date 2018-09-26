package ro.absolute.cleanapparchitecturedataflowdependency.di

import org.koin.android.architecture.ext.viewModel
import org.koin.dsl.module.applicationContext
import ro.absolute.cleanapparchitecturedataflowdependency.presentation.viewmodel.DataViewModel
import ro.absolute.data.DataRepositoryImpl
import ro.absolute.domain.repository.DataRepository
import ro.absolute.domain.usecase.GetData
import ro.absolute.domain.usecase.GetDataUseCase

/**
 * Created by Alina on 9/10/2018.
 */
val dataModule = applicationContext {
    viewModel { DataViewModel(get()) }
    bean { DataRepositoryImpl() as DataRepository }
    bean { GetDataUseCase(get()) as GetData }
}