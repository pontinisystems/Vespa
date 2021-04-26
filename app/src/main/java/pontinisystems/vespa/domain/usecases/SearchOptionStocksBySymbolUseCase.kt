package pontinisystems.vespa.domain.usecases

import pontinisystems.vespa.coreapp.Either
import pontinisystems.vespa.coreapp.Failure
import pontinisystems.vespa.domain.entities.OptionStockFavoriteUi

interface SearchOptionStocksBySymbolUseCase{
    suspend operator fun invoke(searchText: String?):Either<List<OptionStockFavoriteUi>, Failure>
}

