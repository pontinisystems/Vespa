package pontinisystems.vespa.presenter.products.ui.viewaction

import pontinisystems.vespa.domain.entities.ProductUi

sealed class ItemProductAction {

    object Fetch: ItemProductAction()
    data class AddItem(val productUi: ProductUi): ItemProductAction()
}