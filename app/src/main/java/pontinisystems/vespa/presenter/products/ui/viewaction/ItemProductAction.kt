package pontinisystems.vespa.presenter.products.ui.viewaction

sealed class ItemProductAction {

    object Fetch: ItemProductAction()
    object Init: ItemProductAction()
}