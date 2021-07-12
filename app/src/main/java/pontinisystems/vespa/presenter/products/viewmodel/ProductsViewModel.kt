package pontinisystems.vespa.presenter.products.viewmodel

import android.util.Log
import kotlinx.coroutines.Job
import pontinisystems.vespa.coreapp.*
import pontinisystems.vespa.domain.entities.ProductUi
import pontinisystems.vespa.domain.usecases.GetProducts
import pontinisystems.vespa.presenter.products.ui.viewaction.ItemProductAction
import pontinisystems.vespa.presenter.products.ui.viewstate.ProductViewState

class ProductsViewModel(
    private val dispatcherProvider: DispactcherProvider,
    private val getProducts: GetProducts,
) : BaseViewModel<ProductViewState, ItemProductAction>(),
    ActionDispatcher<ItemProductAction> {

    protected var uiStateJob: Job? = null
    override val viewState: ProductViewState = ProductViewState()

    override fun dispatchViewAction(viewAction: ItemProductAction) {
        when (viewAction) {
            is ItemProductAction.Fetch -> fetch()

            else -> TODO()
        }
    }


    private fun fetch() {
        setSuccesssScenario(
            listOf(
            ProductUi(name = "Colera", amount = 10, price = 3.80),
            ProductUi(name = "Osso", amount = 90,price = 3.74),
            ProductUi(name = "Cenoura", amount = 14,price = 3.9),
        ))
    }


    private fun setErrorScenario(error: Failure) {
        Log.i("ERRROR", "ERROR$error")
    }

    private suspend fun setErrorScenario(error: String?) {
        setState(ProductViewState.State.ERROR)
    }

    private  fun setSuccesssScenario(data: List<ProductUi>?) {
        data?.let {
            viewState.action.value = (ProductViewState.Action.SetFavoriteStocksList(it))
            setState(ProductViewState.State.SUCCESS)

        }?: run {
            viewState.action.value = (ProductViewState.Action.SetFavoriteStocksList(listOf()))
            setState(ProductViewState.State.SUCCESS)
        }
    }

    private  fun setState(state: ProductViewState.State) {
        viewState.state.value = (state)
    }
}