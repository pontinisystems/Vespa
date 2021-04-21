package pontinisystems.vespa.domain.usecases.impl

import kotlinx.coroutines.flow.Flow
import pontinisystems.vespa.coreapp.Either
import pontinisystems.vespa.coreapp.Failure
import pontinisystems.vespa.coreapp.Resource
import pontinisystems.vespa.domain.entities.StockFavoriteUi
import pontinisystems.vespa.domain.repositories.StockRepository
import pontinisystems.vespa.domain.usecases.GetFavoriteStocks

class GetFavoriteStocksImpl(val stockRepository: StockRepository) :
    GetFavoriteStocks {
    override suspend fun invoke(): Either<List<StockFavoriteUi>, Failure> {
        return when (val result = stockRepository.getAllStockFavorite()) {
            is Either.Success -> Either.Success(result.data)
            is Either.Error -> Either.Error(result.error)
        }
    }

    override suspend fun invokeV2(): Flow<Resource<List<StockFavoriteUi>>> {
       return stockRepository.getAllStockFavoriteV2()
    }

}