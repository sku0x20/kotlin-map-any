package com.myeglu.common.kotlinx.serialization

import kotlinx.serialization.json.*

class ToJsonElementTransformer {

    fun transformMap(map: Map<*, *>): JsonObject {
        val elemMap = LinkedHashMap<String, JsonElement>()
        for ((key, value) in map) {
            elemMap[key as String] = getElement(value)
        }
        return JsonObject(elemMap)
    }

    fun transformList(list: List<*>): JsonArray {
        val array = arrayListOf<JsonElement>()
        for (item in list) {
            array.add(getElement(item))
        }
        return JsonArray(array)
    }

    private fun getElement(value: Any?): JsonElement {
        return when (value) {
            null -> JsonNull
            is Map<*, *> -> transformMap(value)
            is List<*> -> transformList(value)
            else -> getPrimitive(value)
        }
    }

    private fun getPrimitive(value: Any): JsonPrimitive {
        return when (value) {
            is Boolean -> JsonPrimitive(value)
            is Number -> JsonPrimitive(value)
            is String -> JsonPrimitive(value)
            else -> throw UnknownPrimitiveTypeException(value)
        }
    }

}