package pontinisystems.vespa.presenter.favorite_stocks.viewmodel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import pontinisystems.vespa.coreapp.BaseViewModel
import pontinisystems.vespa.coreapp.DispactcherProvider
import pontinisystems.vespa.presenter.select_new_favorite_stock.ui.viewaction.SelectNewFavoriteStockAction
import pontinisystems.vespa.presenter.favorite_stocks.ui.viewstate.FavoriteStocksViewState
import pontinisystems.vespa.presenter.favorite_stocks.ui.viewstate.FavoriteStocksViewState.FavoriteStocksManagerState

class FavoriteStocksViewModel(private val dispatcherProvider: DispactcherProvider) : BaseViewModel<FavoriteStocksViewState, SelectNewFavoriteStockAction>() {

    override val viewState: FavoriteStocksViewState= FavoriteStocksViewState()

    override fun dispatchViewAction(viewAction: SelectNewFavoriteStockAction) {
       when(viewAction){
            is SelectNewFavoriteStockAction.FetchFavoriteStock->fetchFavoriteStocks()

           else -> TODO()
       }
    }

    private fun fetchFavoriteStocks() {
        setLoadingScnerario()
    }

    private fun setLoadingScnerario() {
        viewState.state.value= FavoriteStocksManagerState.LOADING
        val list = viewModelScope.async(dispatcherProvider.io()){

        }
        viewModelScope.launch {
            when(val result = list.await()){

            }
        }

    }


}