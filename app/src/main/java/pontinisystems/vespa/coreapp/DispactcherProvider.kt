package pontinisystems.vespa.coreapp

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface  DispactcherProvider {
    fun main():CoroutineDispatcher =Dispatchers.Main
    fun default():CoroutineDispatcher =Dispatchers.Default
    fun io():CoroutineDispatcher =Dispatchers.IO
    fun unconfined():CoroutineDispatcher =Dispatchers.Unconfined

}

class DefaultDispatcherProvider:DispactcherProvider