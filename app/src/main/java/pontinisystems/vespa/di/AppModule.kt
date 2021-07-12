package pontinisystems.vespa.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import pontinisystems.vespa.coreapp.DefaultDispatcherProvider
import pontinisystems.vespa.coreapp.DispactcherProvider
import pontinisystems.vespa.domain.repositories.ProductRepository
import pontinisystems.vespa.domain.usecases.GetProducts
import pontinisystems.vespa.domain.usecases.impl.GetProductsImpl
import pontinisystems.vespa.infra.repositories.ProductRepositoryImpl
import pontinisystems.vespa.presenter.products.viewmodel.ProductsViewModel

val appModule = module {
    viewModel{
        ProductsViewModel(
            get(),get()
        )


    }



    single<DispactcherProvider> {DefaultDispatcherProvider()  }
    single<GetProducts> { GetProductsImpl(get())  }

    single<ProductRepository> {ProductRepositoryImpl()  }

}