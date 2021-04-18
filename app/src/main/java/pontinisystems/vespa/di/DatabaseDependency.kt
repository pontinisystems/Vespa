package pontinisystems.vespa.di

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import pontinisystems.vespa.infra.database.Database

val databaseModule = module {
   /* single { SharedPreferenceUtils.getInstance(get()) }*/
    /*single { AppDatabase.getInstance(get()) }
    single { get<AppDatabase>().issuesDao() }*/

    single { Database(androidContext()) }
    single { get<Database>().optionStockFavoriteDao() }
    single { get<Database>().stockFavoriteDao() }
}