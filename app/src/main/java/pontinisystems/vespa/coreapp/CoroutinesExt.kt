package pontinisystems.vespa.coreapp

import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlin.coroutines.coroutineContext


// TODO https://stackoverflow.com/questions/59680533/how-to-cancel-unsubscribe-from-coroutines-flow

suspend inline fun <T> Flow<T>.safeCollect(crossinline action: suspend (T) -> Unit) {
    collect {
        coroutineContext.ensureActive()
        action(it)
    }
}