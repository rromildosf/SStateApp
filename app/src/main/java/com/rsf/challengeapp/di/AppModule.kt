package com.rsf.challengeapp.di

import com.rsf.challengeapp.datasource.IProductDataSource
import com.rsf.challengeapp.datasource.ProductDataSource
import com.rsf.challengeapp.repository.IProductRepository
import com.rsf.challengeapp.repository.ProductRepository
import com.rsf.challengeapp.service.model.ApiServiceFactory
import com.rsf.challengeapp.service.ItemService
import com.rsf.challengeapp.util.Constants
import com.rsf.challengeapp.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module


class AppModule {

    private val dataSourceModule = module {
        single { ApiServiceFactory().createService(ItemService::class.java, Constants.SERVICE_API) }
        single<IProductDataSource> { ProductDataSource(get()) }
    }

    private val repositoryModule = module {
        single<IProductRepository> { ProductRepository(get()) }
    }

    private val viewModelModule = module {
        viewModel { MainViewModel(get()) }
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