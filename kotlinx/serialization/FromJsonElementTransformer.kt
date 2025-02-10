package com.myeglu.common.kotlinx.serialization

import kotlinx.serialization.json.*

class FromJsonElementTransformer {

    fun transformObject(jsonObject: JsonObject): MutableMap<String, Any?> {
        val map = LinkedHashMap<String, Any?>()
        for ((key, value) in jsonObject) {
            map[key] = getValue(value)
        }
        return map
    }

    fun transformArray(jsonArray: JsonArray): MutableList<Any?> {
        val list = arrayListOf<Any?>()
        for (elem in jsonArray) {
            list.add(getValue(elem))
        }
        return list
    }

    private fun getValue(jsonElement: JsonElement): Any? {
        return when (jsonElement) {
            is JsonObject -> transformObject(jsonElement)
            is JsonArray -> transformArray(jsonElement)
            is JsonNull -> null
            is JsonPrimitive -> getPrimitive(jsonElement)
            else -> throw UnknownJsonTypeException(jsonElement)
        }
    }

    // https://github.com/Kotlin/kotlinx.serialization/issues/1298
    private fun getPrimitive(elem: JsonPrimitive): Any {
        return when {
            elem.isString -> elem.content
            elem.booleanOrNull != null -> elem.boolean
            elem.longOrNull != null -> elem.long
            elem.doubleOrNull != null -> elem.double
            else -> throw UnknownJsonTypeException(elem)
        }
    }
}