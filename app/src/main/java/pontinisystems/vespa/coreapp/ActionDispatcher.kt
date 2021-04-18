package pontinisystems.vespa.coreapp

interface ActionDispatcher<VA>{
    fun dispatchViewAction(viewAction:VA)
}