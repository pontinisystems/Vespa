package pontinisystems.vespa.presenter.favorite_stocks.ui.viewstate

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.MutableStateFlow
import pontinisystems.vespa.domain.entities.OptionStockFavoriteUi
import pontinisystems.vespa.domain.entities.StockFavoriteUi
import pontinisystems.vespa.presenter.select_new_favorite_stock.ui.viewstate.SelectNewFavoriteStockViewState

class FavoriteStocksViewState
{

    val action = MutableStateFlow<Action>(Action.Init)
    val state= MutableStateFlow<State>(State.LOADING)

    val data = MutableLiveData<List<StockFavoriteUi>>().apply { value = mutableListOf() }


    sealed class Action{
        object Init : Action()
        data class SetFavoriteStocksList(val list :List<StockFavoriteUi>):
            FavoriteStocksViewState.Action()
    }
    val isLoadingState: LiveData<Int> = Transformations.map(state.asLiveData()) {
        if (it == FavoriteStocksViewState.State.LOADING ) {
            Log.i("Loading","Loading")
            View.VISIBLE
        }else{
            View.GONE
        }
    }
    val isSuccess: LiveData<Int> = Transformations.map(state.asLiveData()) {
        if (it == FavoriteStocksViewState.State.SUCCESS ) {
            Log.i("Loading","Loading")
            View.VISIBLE
        }else{
            View.GONE
        }
    }

    enum class State{
        LOADING, SUCCESS, ERROR
    }
}

