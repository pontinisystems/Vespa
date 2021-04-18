package pontinisystems.vespa.infra.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "stock-favorite")
data class StockFavoriteEntity(

    @SerializedName("symbol")
    @PrimaryKey
    val symbol: String,
    @SerializedName("name")
    val name: String

)