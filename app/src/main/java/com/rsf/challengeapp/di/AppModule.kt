package com.rsf.challengeapp.di

import com.rsf.challengeapp.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module


class AppModule {

    private val dataSourceModule = module {

    }

    private val repositoryModule = module {

    }

    private val viewModelModule = module {
        viewModel { MainViewModel() }
    }

    val subModules: List<Module>
        get() {
            return mutableListOf(
                repositoryModule,
                viewModelModule,
                dataSourceModule
            )
        }
}