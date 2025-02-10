package com.example.kotlinx.serialization

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MapAnyTest {

    @Test
    fun deserialize() {
        val raw = rawString()
        val decoded = Json.decodeFromString<MapAny>(raw)
        assertThat(LinkedHashMap(decoded)).isEqualTo(rawMap())
    }

    @OptIn(ExperimentalSerializationApi::class)
    @Suppress("UNCHECKED_CAST")
    @Test
    fun serialize() {
        val json = Json {
            prettyPrint = true
            prettyPrintIndent = "  "
        }
        val mapAny = MapAny(rawMap() as HashMap<String, Any?>)
        val encoded = json.encodeToString(mapAny)
        assertThat(encoded).isEqualTo(rawString())
    }

    private fun rawString() = """{
  "null": null,
  "string": "string",
  "boolean": true,
  "long": 1,
  "double": 1.1,
  "object": {
    "innerObject": {
      "k1": "v1"
    }
  },
  "array": [
    {
      "longA": [
        1,
        2,
        3
      ]
    },
    {
      "string": [
        "1",
        "2",
        "3"
      ]
    }
  ]
}"""

    // can use any map,
    // using linkedMap to preserve order and make string assertion simple
    private fun rawMap() = linkedMapOf(
        "null" to null,
        "string" to "string",
        "boolean" to true,
        "long" to 1L,
        "double" to 1.1,
        "object" to linkedMapOf(
            "innerObject" to linkedMapOf(
                "k1" to "v1"
            )
        ),
        "array" to arrayListOf(
            linkedMapOf(
                "longA" to arrayListOf(1L, 2L, 3L),
            ),
            linkedMapOf(
                "string" to arrayListOf("1", "2", "3"),
            )
        )
    )

}