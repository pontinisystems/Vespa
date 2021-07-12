package pontinisystems.vespa.domain.usecases.impl

import pontinisystems.vespa.coreapp.Either
import pontinisystems.vespa.coreapp.Failure
import pontinisystems.vespa.domain.entities.ProductUi
import pontinisystems.vespa.domain.repositories.ProductRepository
import pontinisystems.vespa.domain.usecases.GetProducts

class GetProductsImpl(val productRepository: ProductRepository) :
    GetProducts {
    override suspend fun invoke(): Either<List<ProductUi>, Failure> {
       return Either.Success(listOf<ProductUi>())
    }

}