package pontinisystems.vespa

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import pontinisystems.vespa.di.apiModule
import pontinisystems.vespa.di.appModule
import pontinisystems.vespa.di.databaseModule
import pontinisystems.vespa.di.retrofitModule

class VespaApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@VespaApp)
            modules(provideModules())
        }
    }

    private fun provideModules() = listOf(retrofitModule, apiModule, appModule, databaseModule)
}