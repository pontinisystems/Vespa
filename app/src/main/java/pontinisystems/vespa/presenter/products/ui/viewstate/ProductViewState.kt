package pontinisystems.vespa.presenter.products.ui.viewstate

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import pontinisystems.vespa.domain.entities.ProductUi

class ProductViewState
{

    val action = MutableLiveData<Action>(Action.Init)
    val state= MutableLiveData<State>(State.LOADING)

    val data = MutableLiveData<List<ProductUi>>().apply { value = mutableListOf() }


    sealed class Action{
        object Init : Action()
        data class OpenDialog(val productUi: ProductUi) : Action()
        data class SetFavoriteStocksList(val list :List<ProductUi>):ProductViewState.Action()
    }
    val isLoadingState: LiveData<Int> = Transformations.map(state) {
        if (it == ProductViewState.State.LOADING ) {
            Log.i("Loading","Loading")
            View.VISIBLE
        }else{
            View.GONE
        }
    }

    val isError: LiveData<Int> = Transformations.map(state) {
        if (it == ProductViewState.State.ERROR ) {
            Log.i("ERROR","ERROR")
            View.VISIBLE
        }else{
            View.GONE
        }
    }

    val isEmpty: LiveData<Int> = Transformations.map(state) {
        if (it == ProductViewState.State.SUCCESS && data.value.isNullOrEmpty() ) {
            Log.i("ERROR","ERROR")
            View.VISIBLE
        }else{
            View.GONE
        }
    }



    val isSuccess: LiveData<Int> = Transformations.map(state) {
        if (it == ProductViewState.State.SUCCESS ) {
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

