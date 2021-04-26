package pontinisystems.vespa.infra.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "option-stock-favorite")
data class OptionStockFavoriteEntity(

    @SerializedName("symbol")
    @PrimaryKey
    val symbol: String,
    @SerializedName("name")
    val name: String

)