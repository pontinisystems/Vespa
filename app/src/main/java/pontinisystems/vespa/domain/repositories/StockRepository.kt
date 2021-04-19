package pontinisystems.vespa.domain.repositories

import pontinisystems.vespa.coreapp.Either
import pontinisystems.vespa.coreapp.Failure
import pontinisystems.vespa.domain.entities.OptionStockFavoriteUi
import pontinisystems.vespa.domain.entities.StockFavoriteUi
import pontinisystems.vespa.infra.database.entities.StockFavoriteEntity

interface StockRepository {
    suspend fun searchStocksBySimbol(searchText:String):Either<List<OptionStockFavoriteUi>, Failure>
    suspend fun saveFavoriteStock(data:OptionStockFavoriteUi):Either<Long, Failure>
    suspend fun removeFavoriteStock(data:OptionStockFavoriteUi):Either<Boolean, Failure>
    suspend fun getAllStockFavorite(forceUpdate:Boolean=true):Either<List<StockFavoriteUi>,Failure>
}