package pontinisystems.vespa.domain.usecases

import pontinisystems.vespa.coreapp.Either
import pontinisystems.vespa.coreapp.Failure
import pontinisystems.vespa.domain.entities.ProductUi

interface GetProducts{
    suspend operator fun invoke(): Either<List<ProductUi>, Failure>
}

