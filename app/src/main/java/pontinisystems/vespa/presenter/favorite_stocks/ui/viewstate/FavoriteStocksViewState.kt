package pontinisystems.vespa.presenter.favorite_stocks.ui.viewstate

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.MutableStateFlow

class FavoriteStocksViewState
{

    val action = MutableLiveData<Action>()
    val state= MutableLiveData<FavoriteStocksManagerState>()

    sealed class Action{
        class OpenFavoriteStock(val id:String):Action()
    }


    enum class FavoriteStocksManagerState{
        LOADING, SUCCESS, ERROR
    }
}

