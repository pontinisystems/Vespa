package pontinisystems.vespa.infra.repositories

import android.util.Log
import org.json.JSONArray
import org.json.JSONObject
import pontinisystems.vespa.coreapp.Either
import pontinisystems.vespa.coreapp.Failure
import pontinisystems.vespa.coreapp.toMutableList
import pontinisystems.vespa.domain.entities.OptionStockFavoriteUi
import pontinisystems.vespa.domain.repositories.StockRepository
import pontinisystems.vespa.external.StockApi
import pontinisystems.vespa.infra.database.dao.OptionStockFavoriteDao
import pontinisystems.vespa.infra.database.dao.StockFavoriteDao
import pontinisystems.vespa.infra.mappers.JsonObjectToOptionStockFavoriteEntityMapper
import pontinisystems.vespa.infra.mappers.JsonObjectToOptionStockFavoriteUiMapper
import pontinisystems.vespa.infra.mappers.OptionStockFavoriteUiToStockFavoriteEntityMapper
import retrofit2.HttpException
import java.lang.Exception

class StockRepositoryImpl(
    private val stockApi: StockApi,
    private val optionStockFavoriteDao: OptionStockFavoriteDao,
    private val stockFavoriteDao: StockFavoriteDao,
    private val jsonObjectToOptionStockFavoriteUiMapper: JsonObjectToOptionStockFavoriteUiMapper,
    private val jsonObjectToStockOptionNewFavoriteEntity: JsonObjectToOptionStockFavoriteEntityMapper,
    private val optionStockFavoriteUiToStockFavoriteEntityMapper: OptionStockFavoriteUiToStockFavoriteEntityMapper,
) : StockRepository {
    override suspend fun searchStocksBySimbol(searchText: String): Either<List<OptionStockFavoriteUi>, Failure> {
        return try {
            val result = stockApi.searchForSymbol(searchText)
            val optionsFavoriteStocks = JSONObject(result.string())["bestMatches"] as JSONArray


            val selectFavoriteStocksUI = optionsFavoriteStocks.toMutableList().map {
                val item = stockFavoriteDao.selectItemStockFavorite(it["1. symbol"] as String)
                Log.i("ITEM ","ITEM ---> "+item.toString())
                jsonObjectToOptionStockFavoriteUiMapper.mapFromTwo(it,item!=null)

            }
            val selectFavoriteStocksEntity = optionsFavoriteStocks.toMutableList().map {
                jsonObjectToStockOptionNewFavoriteEntity.mapFrom(it)
            }
            selectFavoriteStocksEntity.forEach {
                optionStockFavoriteDao.insertOptionSelectStockFavorite(it)
            }

            val count = optionStockFavoriteDao.count()
            Log.i("COUNT ---<> ", "COUNT -----<>" + count)
            Either.Success(selectFavoriteStocksUI)
        } catch (e: HttpException) {
            Either.Error(Failure.NetworkFailure(1, "asdasd"))
        } catch (e: Exception) {
            Either.Error(Failure.NetworkFailure(1, "asdasd"))
        }
    }

    override suspend fun saveFavoriteStock(data: OptionStockFavoriteUi): Either<Long, Failure> {
        return try {
            val result = stockFavoriteDao.insertStockFavorite(optionStockFavoriteUiToStockFavoriteEntityMapper.mapFrom(data))
            Either.Success(result)
        } catch (e: HttpException) {
            Either.Error(Failure.NetworkFailure(1, "asdasd"))
        } catch (e: Exception) {
            Either.Error(Failure.NetworkFailure(1, "asdasd"))
        }
    }

    override suspend fun removeFavoriteStock(data: OptionStockFavoriteUi): Either<Boolean, Failure> {
        return try {
             stockFavoriteDao.delete(optionStockFavoriteUiToStockFavoriteEntityMapper.mapFrom(data))
            Either.Success(true)
        } catch (e: HttpException) {
            Either.Error(Failure.NetworkFailure(1, "asdasd"))
        } catch (e: Exception) {
            Either.Error(Failure.NetworkFailure(1, "asdasd"))
        }
    }


}
