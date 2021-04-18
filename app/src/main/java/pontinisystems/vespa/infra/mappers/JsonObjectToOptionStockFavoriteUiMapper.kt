package pontinisystems.vespa.infra.mappers

import org.json.JSONObject
import pontinisystems.vespa.coreapp.MapperTwo
import pontinisystems.vespa.domain.entities.OptionStockFavoriteUi

class JsonObjectToOptionStockFavoriteUiMapper : MapperTwo<JSONObject,Boolean, OptionStockFavoriteUi> {
    override fun mapFromTwo(from: JSONObject,checked:Boolean): OptionStockFavoriteUi = OptionStockFavoriteUi(
        symbol = (from["1. symbol"] as String).orEmpty(),
        name = (from["2. name"] as String).orEmpty(),
        checked=checked
    )
}