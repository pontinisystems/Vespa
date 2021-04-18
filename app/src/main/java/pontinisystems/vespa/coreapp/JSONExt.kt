package pontinisystems.vespa.coreapp

import org.json.JSONArray
import org.json.JSONObject

fun JSONArray.toMutableList(): MutableList<JSONObject> = MutableList(length(), this::getJSONObject)
