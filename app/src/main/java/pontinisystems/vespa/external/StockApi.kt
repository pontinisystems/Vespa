package pontinisystems.vespa.external

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface StockApi {

    /*    @GET("/query?function=SYMBOL_SEARCH&keywords=CVC&apikey=O42CEXF2N4E4KQWY")
        suspend fun searchForSymbol(): ResponseBody*/
    @GET("/query")
    suspend fun searchForSymbol(@Query("keywords") keywords: String, @Query("apikey") apikey: String="O42CEXF2N4E4KQWY",@Query("function") function: String="SYMBOL_SEARCH"): ResponseBody

}