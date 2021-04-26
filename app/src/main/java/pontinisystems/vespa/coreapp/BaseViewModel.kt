package pontinisystems.vespa.coreapp

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job

abstract class BaseViewModel<VS, VA> : ViewModel() {
    abstract val viewState: VS
    abstract fun dispatchViewAction(viewAction: VA)



}
