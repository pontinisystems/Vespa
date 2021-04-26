package pontinisystems.vespa.presenter.favorite_stocks.viewmodel

import android.util.Log
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import pontinisystems.vespa.coreapp.*
import pontinisystems.vespa.domain.entities.StockFavoriteUi
import pontinisystems.vespa.domain.usecases.GetFavoriteStocks
import pontinisystems.vespa.presenter.favorite_stocks.ui.viewaction.FavoriteStocksAction
import pontinisystems.vespa.presenter.favorite_stocks.ui.viewstate.FavoriteStocksViewState
import kotlin.coroutines.coroutineContext

class FavoriteStocksViewModel(
    private val dispatcherProvider: DispactcherProvider,
    private val getFavoriteStocks: GetFavoriteStocks,
) : BaseViewModel<FavoriteStocksViewState, FavoriteStocksAction>(),
    ActionDispatcher<FavoriteStocksAction> {

    protected var uiStateJob: Job? = null
    override val viewState: FavoriteStocksViewState = FavoriteStocksViewState()

    override fun dispatchViewAction(viewAction: FavoriteStocksAction) {
        when (viewAction) {
            is FavoriteStocksAction.FetchFavoritesStocks -> fetchFavoriteStocksV2()

            else -> TODO()
        }
    }


    private fun fetchFavoriteStocksV2() {
        uiStateJob = viewModelScope.async(dispatcherProvider.io()) {
            setState(FavoriteStocksViewState.State.LOADING)
            getFavoriteStocks.invokeV2().collect() {

                Log.i(" Emit --> Collect"," Emit --> Collect --> fetchFavoriteStocksV2")

                if (it.status == Resource.Status.ERROR) {
                    setErrorScenario(it.message)
                } else if (it.status == Resource.Status.SUCCESS) {
                    setSuccesssScenario(it.data)
                }

            }
        }
        
    }


    private fun setErrorScenario(error: Failure) {
        Log.i("ERRROR", "ERROR$error")
    }

    private fun setErrorScenario(error: String?) {
        Log.i("ERRROR", "ERROR" + error.toString())
    }

    private suspend fun setSuccesssScenario(data: List<StockFavoriteUi>?) {
        data?.let {
            viewState.action.emit(FavoriteStocksViewState.Action.SetFavoriteStocksList(it))
            setState(FavoriteStocksViewState.State.SUCCESS)

        }?: run {
            viewState.action.emit(FavoriteStocksViewState.Action.SetFavoriteStocksList(listOf()))
            setState(FavoriteStocksViewState.State.SUCCESS)
        }
    }

    private suspend fun setState(state: FavoriteStocksViewState.State) {
        viewState.state.emit(state)
    }

    fun onStop() {
        Log.i("Emit Chamou o onStop","Emit Chamou o onStop")
        uiStateJob?.cancel()
    }

}