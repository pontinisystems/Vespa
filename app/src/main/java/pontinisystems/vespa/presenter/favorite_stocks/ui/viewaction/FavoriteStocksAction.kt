package pontinisystems.vespa.presenter.favorite_stocks.ui.viewaction

sealed class FavoriteStocksAction {

    object FetchFavoritesStocks: FavoriteStocksAction()
    object Init: FavoriteStocksAction()
}