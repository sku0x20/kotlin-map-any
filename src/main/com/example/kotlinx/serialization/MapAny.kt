package com.example.kotlinx.serialization

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonObject

@Serializable(with = MapAnySerializer::class)
class MapAny(
    private val delegate: MutableMap<String, Any?> = LinkedHashMap()
) : MutableMap<String, Any?> by delegate

class MapAnySerializer : KSerializer<MapAny> {

    private val jsonObjectSerializer = JsonObject.serializer()

    @OptIn(ExperimentalSerializationApi::class)
    override val descriptor: SerialDescriptor =
        SerialDescriptor("com.example.MapAnySerializer", jsonObjectSerializer.descriptor)

    private val deserializeTransformer = FromJsonElementTransformer()
    override fun deserialize(decoder: Decoder): MapAny {
        val jsonElemMap = decoder.decodeSerializableValue(jsonObjectSerializer)
        val map = deserializeTransformer.transformObject(jsonElemMap)
        return MapAny(map)
    }

    private val serializeTransformer = ToJsonElementTransformer()
    override fun serialize(encoder: Encoder, value: MapAny) {
        val jsonObject = serializeTransformer.transformMap(value)
        encoder.encodeSerializableValue(jsonObjectSerializer, jsonObject)
    }

}
