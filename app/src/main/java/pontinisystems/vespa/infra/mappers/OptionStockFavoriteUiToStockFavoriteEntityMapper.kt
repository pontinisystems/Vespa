package pontinisystems.vespa.infra.mappers

import org.json.JSONObject
import pontinisystems.vespa.coreapp.Mapper
import pontinisystems.vespa.domain.entities.OptionStockFavoriteUi
import pontinisystems.vespa.infra.database.entities.OptionStockFavoriteEntity
import pontinisystems.vespa.infra.database.entities.StockFavoriteEntity

class OptionStockFavoriteUiToStockFavoriteEntityMapper : Mapper<OptionStockFavoriteUi, StockFavoriteEntity> {
    override fun mapFrom(from: OptionStockFavoriteUi): StockFavoriteEntity = StockFavoriteEntity(
        symbol = from.symbol,
        name = from.name

    )
}