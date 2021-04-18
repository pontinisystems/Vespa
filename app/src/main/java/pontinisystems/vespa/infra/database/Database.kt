package pontinisystems.vespa.infra.database

import android.content.Context
import androidx.room.Room
import pontinisystems.vespa.infra.database.dao.OptionStockFavoriteDao
import pontinisystems.vespa.infra.database.dao.StockFavoriteDao

class Database(context: Context) {

    companion object {
        const val DB_NAME = "database-vespa"
    }

    private val db = Room.databaseBuilder(context, DataBaseBuilder::class.java, DB_NAME)
        .fallbackToDestructiveMigration().build()

    fun optionStockFavoriteDao(): OptionStockFavoriteDao {
        return db.optionStockFavoriteEntityDaO()
    }
    fun stockFavoriteDao(): StockFavoriteDao {
        return db.stockFavoriteEntityDaO()
    }

}