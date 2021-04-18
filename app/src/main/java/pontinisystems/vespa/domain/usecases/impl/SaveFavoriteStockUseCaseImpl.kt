package pontinisystems.vespa.domain.usecases.impl

import pontinisystems.vespa.coreapp.Either
import pontinisystems.vespa.coreapp.Failure
import pontinisystems.vespa.domain.entities.OptionStockFavoriteUi
import pontinisystems.vespa.domain.repositories.StockRepository
import pontinisystems.vespa.domain.usecases.SaveFavoriteStockUseCase

class SaveFavoriteStockUseCaseImpl(val stockRepository: StockRepository) :
    SaveFavoriteStockUseCase {
    override suspend fun invoke(data: OptionStockFavoriteUi): Either<Long, Failure> {
        data.let {
            return when (val result = stockRepository.saveFavoriteStock(data)) {
                is Either.Success -> Either.Success(result.data)
                is Either.Error -> Either.Error(result.error)
            }
        }


    }


}