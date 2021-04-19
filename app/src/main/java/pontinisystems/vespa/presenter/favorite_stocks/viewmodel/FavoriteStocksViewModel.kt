package pontinisystems.vespa.presenter.favorite_stocks.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import pontinisystems.vespa.coreapp.*
import pontinisystems.vespa.domain.entities.StockFavoriteUi
import pontinisystems.vespa.domain.usecases.GetFavoriteStocks
import pontinisystems.vespa.presenter.favorite_stocks.ui.viewaction.FavoriteStocksAction
import pontinisystems.vespa.presenter.favorite_stocks.ui.viewstate.FavoriteStocksViewState

class FavoriteStocksViewModel(
    private val dispatcherProvider: DispactcherProvider,
    private val getFavoriteStocks: GetFavoriteStocks,
) : BaseViewModel<FavoriteStocksViewState, FavoriteStocksAction>(),
    ActionDispatcher<FavoriteStocksAction> {

    override val viewState: FavoriteStocksViewState = FavoriteStocksViewState()

    override fun dispatchViewAction(viewAction: FavoriteStocksAction) {
        when (viewAction) {
            is FavoriteStocksAction.FetchFavoritesStocks -> fetchFavoriteStocks()

            else -> TODO()
        }
    }

    private fun fetchFavoriteStocks() {
        setState(FavoriteStocksViewState.State.LOADING)
        val list = viewModelScope.async(dispatcherProvider.io()) {
            getFavoriteStocks.invoke()
        }

        viewModelScope.launch {
            when (val result = list.await()) {
                is Either.Success -> {
                    setSuccesssScenario(result.data)

                }
                is Either.Error -> {
                    setErrorScenario(result.error)
                }

            }
        }
    }



    private fun setErrorScenario(error: Failure) {
       Log.i("ERRROR","ERROR"+error.toString())
    }

    private fun setSuccesssScenario(data: List<StockFavoriteUi>) {
        setState(FavoriteStocksViewState.State.SUCCESS)
        viewState.data.value = data
        viewState.action.value=FavoriteStocksViewState.Action.SetFavoriteStocksList(data)
    }
    private fun setState(state: FavoriteStocksViewState.State) {
        viewState.state.value = state
    }
}