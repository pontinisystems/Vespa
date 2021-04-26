package pontinisystems.vespa.coreapp

sealed class Failure{
    open class NetworkFailure(val code:Int, val message:String):Failure()
    open class InputInvalid(val message:String):Failure()
}