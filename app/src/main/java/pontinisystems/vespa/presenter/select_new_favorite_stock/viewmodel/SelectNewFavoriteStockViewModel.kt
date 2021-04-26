package pontinisystems.vespa.presenter.select_new_favorite_stock.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import pontinisystems.vespa.coreapp.*
import pontinisystems.vespa.domain.entities.OptionStockFavoriteUi
import pontinisystems.vespa.domain.usecases.RemoveFavoriteStockUseCase
import pontinisystems.vespa.domain.usecases.SaveFavoriteStockUseCase
import pontinisystems.vespa.domain.usecases.SearchOptionStocksBySymbolUseCase
import pontinisystems.vespa.presenter.select_new_favorite_stock.ui.viewaction.SelectNewFavoriteStockAction
import pontinisystems.vespa.presenter.select_new_favorite_stock.ui.viewstate.SelectNewFavoriteStockViewState

class SelectNewFavoriteStockViewModel(
    private val dispatcherProvider: DispactcherProvider,
    private val searchOptionStocksBySymbolUseCase: SearchOptionStocksBySymbolUseCase,
    private val saveFavoriteStockUseCase: SaveFavoriteStockUseCase,
    private val removeFavoriteStock: RemoveFavoriteStockUseCase,
) : BaseViewModel<SelectNewFavoriteStockViewState, SelectNewFavoriteStockAction>(),
    ActionDispatcher<SelectNewFavoriteStockAction> {

    override val viewState: SelectNewFavoriteStockViewState = SelectNewFavoriteStockViewState()

    override fun dispatchViewAction(viewAction: SelectNewFavoriteStockAction) {
        when (viewAction) {
            is SelectNewFavoriteStockAction.SearchStock -> searchStock(viewAction.searchText)
            is SelectNewFavoriteStockAction.Init -> setupInit()
            is SelectNewFavoriteStockAction.SelectNewFavoriteStock -> selectNewFavoriteStock(viewAction.optSeleFavoriteUi)
            is SelectNewFavoriteStockAction.RemoveFavoriteStock -> removeFavoriteStock(viewAction.optSeleFavoriteUi)

            else -> TODO()
        }
    }



    private fun selectNewFavoriteStock(optSeleFavoriteUi: OptionStockFavoriteUi) {
        val result = viewModelScope.async(dispatcherProvider.io()) {
            saveFavoriteStockUseCase.invoke(optSeleFavoriteUi)
        }
        viewModelScope.launch {
            when (val result = result.await()) {
                is Either.Success -> {

                    Log.i("Gravou com successo","Gravou com successo")
                }
                is Either.Error -> {
                    Log.i("Error  ","ERROR "+result.error.toString())

                }

            }
        }
    }
    private fun removeFavoriteStock(optSeleFavoriteUi: OptionStockFavoriteUi) {
        val result = viewModelScope.async(dispatcherProvider.io()) {
            removeFavoriteStock.invoke(optSeleFavoriteUi)
        }
        viewModelScope.launch {
            when (val result = result.await()) {
                is Either.Success -> {

                    Log.i("Removeu com successo","Removeu com successo")
                }
                is Either.Error -> {
                    Log.i("Error  ","ERROR "+result.error.toString())

                }

            }
        }
    }

    private fun setupInit() {
        viewState.state.value = SelectNewFavoriteStockViewState.State.INIT_SCREEN


    }

    private fun searchStock(searchText: String?) {
        setLoadingScnerario()

        val list = viewModelScope.async(dispatcherProvider.io()) {
            searchOptionStocksBySymbolUseCase.invoke(searchText)
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
        if(error is Failure.InputInvalid){
            setState(SelectNewFavoriteStockViewState.State.INIT_SCREEN)
        }else{
           //TODO criar template de tela de erro
        }
    }

    private fun setSuccesssScenario(data: List<OptionStockFavoriteUi>) {
        setState(SelectNewFavoriteStockViewState.State.SUCCESS)
        viewState.stocksData.value = data
        viewState.action.value=SelectNewFavoriteStockViewState.Action.SetNewFavoriteStocksList(data)
    }

    private fun setLoadingScnerario() {
        viewState.state.value = SelectNewFavoriteStockViewState.State.LOADING
    }

    private fun setState(state: SelectNewFavoriteStockViewState.State) {
        viewState.state.value = state
    }
}