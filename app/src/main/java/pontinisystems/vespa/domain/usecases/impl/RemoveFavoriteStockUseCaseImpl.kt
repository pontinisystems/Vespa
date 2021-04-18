package pontinisystems.vespa.domain.usecases.impl

import pontinisystems.vespa.coreapp.Either
import pontinisystems.vespa.coreapp.Failure
import pontinisystems.vespa.domain.entities.OptionStockFavoriteUi
import pontinisystems.vespa.domain.repositories.StockRepository
import pontinisystems.vespa.domain.usecases.RemoveFavoriteStockUseCase
import pontinisystems.vespa.domain.usecases.SaveFavoriteStockUseCase

class RemoveFavoriteStockUseCaseImpl(val stockRepository: StockRepository) :
    RemoveFavoriteStockUseCase {
    override suspend fun invoke(data: OptionStockFavoriteUi): Either<Boolean, Failure> {
        data.let {
            return when (val result = stockRepository.removeFavoriteStock(data)) {
                is Either.Success -> Either.Success(result.data)
                is Either.Error -> Either.Error(result.error)
            }
        }


    }


}