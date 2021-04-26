package pontinisystems.vespa.infra.mappers

import org.json.JSONObject
import pontinisystems.vespa.coreapp.Mapper
import pontinisystems.vespa.infra.database.entities.OptionStockFavoriteEntity

class JsonObjectToOptionStockFavoriteEntityMapper: Mapper<JSONObject, OptionStockFavoriteEntity> {
    override fun mapFrom(from: JSONObject): OptionStockFavoriteEntity = OptionStockFavoriteEntity(
        symbol = from["1. symbol"] as String,
        name=from    ["2. name"] as String

    )
}