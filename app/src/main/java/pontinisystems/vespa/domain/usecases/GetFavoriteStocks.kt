package pontinisystems.vespa.domain.usecases

import pontinisystems.vespa.coreapp.Either
import pontinisystems.vespa.coreapp.Failure
import pontinisystems.vespa.domain.entities.OptionStockFavoriteUi
import pontinisystems.vespa.domain.entities.StockFavoriteUi

interface GetFavoriteStocks{
    suspend operator fun invoke(): Either<List<StockFavoriteUi>, Failure>
}

