package pontinisystems.vespa.domain.repositories

import kotlinx.coroutines.flow.Flow
import pontinisystems.vespa.coreapp.Either
import pontinisystems.vespa.coreapp.Failure
import pontinisystems.vespa.coreapp.Resource
import pontinisystems.vespa.domain.entities.ProductUi

interface ProductRepository {
  /*  suspend fun searchStocksBySimbol(searchText:String):Either<List<OptionStockFavoriteUi>, Failure>
    suspend fun saveFavoriteStock(data:OptionStockFavoriteUi):Either<Long, Failure>
    suspend fun removeFavoriteStock(data:OptionStockFavoriteUi):Either<Boolean, Failure>
    suspend fun getAllStockFavorite(forceUpdate:Boolean=true):Either<List<ProductUi>,Failure>
    suspend fun getAllStockFavoriteV2(forceUpdate:Boolean=true): Flow<Resource<List<ProductUi>>>*/
}