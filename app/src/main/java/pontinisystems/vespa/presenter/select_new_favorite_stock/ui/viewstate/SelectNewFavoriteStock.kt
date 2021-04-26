package pontinisystems.vespa.presenter.select_new_favorite_stock.ui.viewstate

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import pontinisystems.vespa.domain.entities.OptionStockFavoriteUi

class SelectNewFavoriteStockViewState {

    val action = MutableLiveData<Action>()
    val state = MutableLiveData<State>()

    val isLoadingState: LiveData<Int> = Transformations.map(state) {
        if (it == State.LOADING ) {
            Log.i("Loading","Loading")
           View.VISIBLE
        }else{
            View.GONE
        }
    }
    val isSuccess: LiveData<Int> = Transformations.map(state) {
        if (it == State.SUCCESS ) {
            Log.i("Loading","Loading")
           View.VISIBLE
        }else{
            View.GONE
        }
    }
    val isError: LiveData<Int> = Transformations.map(state) {
        if (it == State.ERROR ) {
            Log.i("ERROR","ERROR")
           View.VISIBLE
        }else{
            View.GONE
        }
    }

    val stocksData = MutableLiveData<List<OptionStockFavoriteUi>>().apply { value = mutableListOf() }

    val isInitScreen: LiveData<Int> = Transformations.map(state) {
        if (it == State.INIT_SCREEN) {
            View.VISIBLE
        }else{
            View.GONE
        }
    }

    sealed class Action {
        data class SetNewFavoriteStocksList(val list :List<OptionStockFavoriteUi>):Action()
    }


    enum class State {
        LOADING, SUCCESS, ERROR, INIT_SCREEN
    }
}

