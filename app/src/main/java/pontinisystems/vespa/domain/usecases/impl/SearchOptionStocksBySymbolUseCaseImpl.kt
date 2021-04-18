package pontinisystems.vespa.domain.usecases.impl

import pontinisystems.vespa.coreapp.Either
import pontinisystems.vespa.coreapp.Failure
import pontinisystems.vespa.domain.entities.OptionStockFavoriteUi
import pontinisystems.vespa.domain.repositories.StockRepository
import pontinisystems.vespa.domain.usecases.SearchOptionStocksBySymbolUseCase

class SearchOptionStocksBySymbolUseCaseImpl(val stockRepository: StockRepository) :
    SearchOptionStocksBySymbolUseCase {
    override suspend fun invoke(searchText: String?): Either<List<OptionStockFavoriteUi>, Failure> {
        searchText?.let {
            if (searchText.length < 3) {
                //TODO refatorar os failures, colocando nas camadas corretas
                return Either.Error(Failure.InputInvalid("Quantidade minima de caracteres inválida"))
            }
            return when (val result = stockRepository.searchStocksBySimbol(searchText)) {
                is Either.Success -> Either.Success(result.data)
                is Either.Error -> Either.Error(result.error)
            }
        } ?: run {
            return Either.Error(Failure.InputInvalid("Texto null"))
        }
    }

}