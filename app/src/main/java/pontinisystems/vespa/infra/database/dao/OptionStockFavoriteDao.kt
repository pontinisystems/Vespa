package pontinisystems.vespa.infra.database.dao

import androidx.room.*
import pontinisystems.vespa.infra.database.entities.OptionStockFavoriteEntity


@Dao
interface OptionStockFavoriteDao {
    @Transaction
    @Query("SELECT * FROM `option-stock-favorite`")
    suspend fun selectStockFavoriteAll():List<OptionStockFavoriteEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOptionSelectStockFavorite(data:OptionStockFavoriteEntity):Long

    @Transaction
    @Query("SELECT COUNT(symbol) FROM `option-stock-favorite`")
    suspend fun count():Integer
}