package com.example.kotlinx.serialization

import com.example.BadRequestException
import kotlinx.serialization.json.JsonElement

class UnknownJsonTypeException(elem: JsonElement) : BadRequestException("unknown json type $elem")