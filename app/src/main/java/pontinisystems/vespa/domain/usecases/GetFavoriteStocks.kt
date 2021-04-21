package pontinisystems.vespa.domain.usecases

import kotlinx.coroutines.flow.Flow
import pontinisystems.vespa.coreapp.Either
import pontinisystems.vespa.coreapp.Failure
import pontinisystems.vespa.coreapp.Resource
import pontinisystems.vespa.domain.entities.OptionStockFavoriteUi
import pontinisystems.vespa.domain.entities.StockFavoriteUi

interface GetFavoriteStocks{
    suspend operator fun invoke(): Either<List<StockFavoriteUi>, Failure>
    suspend fun invokeV2(): Flow<Resource<List<StockFavoriteUi>>>
}

