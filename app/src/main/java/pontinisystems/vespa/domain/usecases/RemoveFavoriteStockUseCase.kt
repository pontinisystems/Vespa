package pontinisystems.vespa.domain.usecases

import pontinisystems.vespa.coreapp.Either
import pontinisystems.vespa.coreapp.Failure
import pontinisystems.vespa.domain.entities.OptionStockFavoriteUi

interface RemoveFavoriteStockUseCase{
    suspend operator fun invoke(optionStockFavoriteUi: OptionStockFavoriteUi): Either<Boolean, Failure>
}

