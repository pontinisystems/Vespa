package pontinisystems.vespa.infra.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import pontinisystems.vespa.infra.database.entities.StockFavoriteEntity


@Dao
interface StockFavoriteDao {
    @Transaction
    @Query("SELECT * FROM `stock-favorite`")
    suspend fun selectStockFavoriteAll():List<StockFavoriteEntity>?


    @Transaction
    @Query("SELECT * FROM `stock-favorite`")
    fun selectStockFavoriteAllV2(): Flow<List<StockFavoriteEntity>?>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStockFavorite(data: StockFavoriteEntity):Long


    @Delete
    suspend fun delete(stockFavoriteEntity: StockFavoriteEntity)

    @Transaction
    @Query("SELECT * FROM `stock-favorite` WHERE symbol=:symbol LIMIT 1")
    suspend fun selectItemStockFavorite(symbol: String):StockFavoriteEntity?

}