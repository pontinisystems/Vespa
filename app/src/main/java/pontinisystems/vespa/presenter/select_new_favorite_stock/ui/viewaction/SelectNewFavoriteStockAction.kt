package pontinisystems.vespa.presenter.select_new_favorite_stock.ui.viewaction

import pontinisystems.vespa.domain.entities.OptionStockFavoriteUi

sealed class SelectNewFavoriteStockAction {

    object FetchFavoriteStock: SelectNewFavoriteStockAction()
    object Init: SelectNewFavoriteStockAction()
    data class SelectNewFavoriteStock(val optSeleFavoriteUi: OptionStockFavoriteUi): SelectNewFavoriteStockAction()
    data class RemoveFavoriteStock(val optSeleFavoriteUi: OptionStockFavoriteUi): SelectNewFavoriteStockAction()
    data class SearchStock(val searchText:String?): SelectNewFavoriteStockAction()
}