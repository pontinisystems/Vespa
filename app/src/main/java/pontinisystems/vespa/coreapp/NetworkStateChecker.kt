package pontinisystems.vespa.coreapp

/**
 * Created by Mohit Rajput on 3/11/2020.
 */
interface NetworkStateChecker {
    fun isNetworkAvailable(): Boolean
}