package pontinisystems.vespa.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import pontinisystems.vespa.coreapp.DefaultDispatcherProvider
import pontinisystems.vespa.coreapp.DispactcherProvider
import pontinisystems.vespa.domain.repositories.StockRepository
import pontinisystems.vespa.domain.usecases.GetFavoriteStocks
import pontinisystems.vespa.domain.usecases.RemoveFavoriteStockUseCase
import pontinisystems.vespa.domain.usecases.SaveFavoriteStockUseCase
import pontinisystems.vespa.domain.usecases.SearchOptionStocksBySymbolUseCase
import pontinisystems.vespa.domain.usecases.impl.GetFavoriteStocksImpl
import pontinisystems.vespa.domain.usecases.impl.RemoveFavoriteStockUseCaseImpl
import pontinisystems.vespa.domain.usecases.impl.SaveFavoriteStockUseCaseImpl
import pontinisystems.vespa.domain.usecases.impl.SearchOptionStocksBySymbolUseCaseImpl
import pontinisystems.vespa.infra.mappers.JsonObjectToOptionStockFavoriteEntityMapper
import pontinisystems.vespa.infra.mappers.JsonObjectToOptionStockFavoriteUiMapper
import pontinisystems.vespa.infra.mappers.OptionStockFavoriteUiToStockFavoriteEntityMapper
import pontinisystems.vespa.infra.mappers.StockFavoriteEntityToStockFavoriteUiMapper
import pontinisystems.vespa.infra.repositories.StockRepositoryImpl
import pontinisystems.vespa.presenter.favorite_stocks.viewmodel.FavoriteStocksViewModel
import pontinisystems.vespa.presenter.select_new_favorite_stock.viewmodel.SelectNewFavoriteStockViewModel

val appModule = module {
    viewModel{
        FavoriteStocksViewModel(
            get(),get()
        )


    }
    viewModel{
        SelectNewFavoriteStockViewModel(
            get(),get(),get(),get()
        )
    }


    single<DispactcherProvider> {DefaultDispatcherProvider()  }
    single<SearchOptionStocksBySymbolUseCase> {SearchOptionStocksBySymbolUseCaseImpl(get())  }
    single<SaveFavoriteStockUseCase> { SaveFavoriteStockUseCaseImpl(get())  }
    single<GetFavoriteStocks> { GetFavoriteStocksImpl(get())  }
    single<RemoveFavoriteStockUseCase> { RemoveFavoriteStockUseCaseImpl(get())  }
    factory { JsonObjectToOptionStockFavoriteUiMapper() }
    factory { JsonObjectToOptionStockFavoriteEntityMapper() }
    factory { OptionStockFavoriteUiToStockFavoriteEntityMapper() }
    factory { StockFavoriteEntityToStockFavoriteUiMapper() }

    single<StockRepository> {StockRepositoryImpl(get(),get(),get(),get(),get(),get(),get())  }

}