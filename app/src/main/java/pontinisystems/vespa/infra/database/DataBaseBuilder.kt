package pontinisystems.vespa.infra.database

import androidx.room.Database
import androidx.room.RoomDatabase
import pontinisystems.vespa.infra.database.DataBaseBuilder.Companion.DB_VERSION
import pontinisystems.vespa.infra.database.dao.OptionStockFavoriteDao
import pontinisystems.vespa.infra.database.dao.StockFavoriteDao
import pontinisystems.vespa.infra.database.entities.OptionStockFavoriteEntity
import pontinisystems.vespa.infra.database.entities.StockFavoriteEntity


@Database(
    entities = [OptionStockFavoriteEntity::class,StockFavoriteEntity::class], version = DB_VERSION
)



abstract class DataBaseBuilder :RoomDatabase(){
    companion object{
        const val DB_VERSION=80
    }


    abstract fun optionStockFavoriteEntityDaO():OptionStockFavoriteDao
    abstract fun stockFavoriteEntityDaO():StockFavoriteDao
}