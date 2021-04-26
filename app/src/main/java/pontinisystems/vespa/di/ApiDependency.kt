package pontinisystems.vespa.di

import org.koin.dsl.module
import pontinisystems.vespa.external.StockApi
import retrofit2.Retrofit

val apiModule = module {
    fun provideStockApi(retrofit: Retrofit) = retrofit.create(StockApi::class.java)
    factory { provideStockApi(retrofit = get()) }
}