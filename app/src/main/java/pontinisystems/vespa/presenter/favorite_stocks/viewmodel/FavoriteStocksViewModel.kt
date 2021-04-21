package pontinisystems.vespa.presenter.favorite_stocks.viewmodel

import android.util.Log
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import okhttp3.internal.wait
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
            is FavoriteStocksAction.FetchFavoritesStocks -> fetchFavoriteStocksV2()

            else -> TODO()
        }
    }



    private fun fetchFavoriteStocksV2() {
        viewModelScope.async(dispatcherProvider.io()) {
            setState(FavoriteStocksViewState.State.LOADING)
            getFavoriteStocks.invokeV2().collect {
                if (it.status == Resource.Status.ERROR) {
                    setErrorScenario(it.message!!)
                } else if (it.status == Resource.Status.SUCCESS) {
                    setSuccesssScenario(it.data!!)
                }

            }
        }


    }


    private fun setErrorScenario(error: Failure) {
        Log.i("ERRROR", "ERROR" + error.toString())
    }

    private fun setErrorScenario(error: String) {
        Log.i("ERRROR", "ERROR" + error.toString())
    }

    private suspend fun setSuccesssScenario(data: List<StockFavoriteUi>) {

            setState(FavoriteStocksViewState.State.SUCCESS)
            viewState.action.emit(FavoriteStocksViewState.Action.SetFavoriteStocksList(data))

        //viewState.data.value = data
    }

    private suspend fun setState(state: FavoriteStocksViewState.State) {
        viewState.state.emit(state)
    }
}