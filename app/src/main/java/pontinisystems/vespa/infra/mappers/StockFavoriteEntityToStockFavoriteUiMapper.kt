package pontinisystems.vespa.infra.mappers

import org.json.JSONObject
import pontinisystems.vespa.coreapp.Mapper
import pontinisystems.vespa.coreapp.MapperTwo
import pontinisystems.vespa.domain.entities.StockFavoriteUi
import pontinisystems.vespa.infra.database.entities.OptionStockFavoriteEntity
import pontinisystems.vespa.infra.database.entities.StockFavoriteEntity

class StockFavoriteEntityToStockFavoriteUiMapper :
    MapperTwo<StockFavoriteEntity, Double, StockFavoriteUi> {
    override fun mapFromTwo(from: StockFavoriteEntity, price: Double): StockFavoriteUi =
        StockFavoriteUi(
            symbol = from.symbol,
            name = from.name,
            price = price


        )
}