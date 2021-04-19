package pontinisystems.vespa.infra.repositories

import android.util.Log
import org.json.JSONArray
import org.json.JSONObject
import pontinisystems.vespa.coreapp.Either
import pontinisystems.vespa.coreapp.Failure
import pontinisystems.vespa.coreapp.toMutableList
import pontinisystems.vespa.domain.entities.OptionStockFavoriteUi
import pontinisystems.vespa.domain.entities.StockFavoriteUi
import pontinisystems.vespa.domain.repositories.StockRepository
import pontinisystems.vespa.external.StockApi
import pontinisystems.vespa.infra.database.dao.OptionStockFavoriteDao
import pontinisystems.vespa.infra.database.dao.StockFavoriteDao
import pontinisystems.vespa.infra.mappers.JsonObjectToOptionStockFavoriteEntityMapper
import pontinisystems.vespa.infra.mappers.JsonObjectToOptionStockFavoriteUiMapper
import pontinisystems.vespa.infra.mappers.OptionStockFavoriteUiToStockFavoriteEntityMapper
import pontinisystems.vespa.infra.mappers.StockFavoriteEntityToStockFavoriteUiMapper
import retrofit2.HttpException
import java.text.SimpleDateFormat
import java.util.*

class StockRepositoryImpl(
    private val stockApi: StockApi,
    private val optionStockFavoriteDao: OptionStockFavoriteDao,
    private val stockFavoriteDao: StockFavoriteDao,
    private val jsonObjectToOptionStockFavoriteUiMapper: JsonObjectToOptionStockFavoriteUiMapper,
    private val jsonObjectToStockOptionNewFavoriteEntity: JsonObjectToOptionStockFavoriteEntityMapper,
    private val entityToStockFavoriteUiMapper: StockFavoriteEntityToStockFavoriteUiMapper,
    private val optionStockFavoriteUiToStockFavoriteEntityMapper: OptionStockFavoriteUiToStockFavoriteEntityMapper,
) : StockRepository {
    override suspend fun searchStocksBySimbol(searchText: String): Either<List<OptionStockFavoriteUi>, Failure> {
        return try {
            val result = stockApi.searchForSymbol(searchText)
            val optionsFavoriteStocks = JSONObject(result.string())["bestMatches"] as JSONArray


            val selectFavoriteStocksUI = optionsFavoriteStocks.toMutableList().map {
                val item = stockFavoriteDao.selectItemStockFavorite(it["1. symbol"] as String)
                Log.i("ITEM ", "ITEM ---> " + item.toString())
                jsonObjectToOptionStockFavoriteUiMapper.mapFromTwo(it, item != null)

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
            val result = stockFavoriteDao.insertStockFavorite(
                optionStockFavoriteUiToStockFavoriteEntityMapper.mapFrom(data)
            )
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

    override suspend fun getAllStockFavorite(forceUpdate: Boolean): Either<List<StockFavoriteUi>, Failure> {
        return try {
            val result = stockFavoriteDao.selectStockFavoriteAll()

            if (result.isNullOrEmpty()) {
                Either.Success(listOf<StockFavoriteUi>())
            } else {
                val date = Date().toSimpleString()

                val stockFavoriteUiList = result.map {
                    val details = stockApi.detailsFavoriteStock(it.symbol)
                    val optionsFavoriteStocks =
                        JSONObject(details.string())["Time Series (Daily)"] as JSONObject
                    val price =
                        (optionsFavoriteStocks["2021-04-16"] as JSONObject)["4. close"] as String

                    entityToStockFavoriteUiMapper.mapFromTwo(it, price.toDouble())
                }
                Either.Success(stockFavoriteUiList)
            }


        } catch (e: HttpException) {
            Either.Error(Failure.NetworkFailure(1, "asdasd"))
        } catch (e: Exception) {
            Either.Error(Failure.NetworkFailure(1, "asdasd"))
        }

    }


}

fun Date.toSimpleString(): String {
    val format = SimpleDateFormat("yyy-MM-dd")
    return format.format(this)
}

fun Date.isDateInCurrentWeek(): Boolean {
    val currentCalendar = Calendar.getInstance()
    val week = currentCalendar[Calendar.WEEK_OF_YEAR]
    val year = currentCalendar[Calendar.YEAR]
    val targetCalendar = Calendar.getInstance()
    targetCalendar.time = this
    val targetWeek = targetCalendar[Calendar.WEEK_OF_YEAR]
    val targetYear = targetCalendar[Calendar.YEAR]
    return week == targetWeek && year == targetYear
}