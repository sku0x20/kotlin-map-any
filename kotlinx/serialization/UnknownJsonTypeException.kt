package com.exmaple.kotlinx.serialization

import com.exmaple.BadRequestException
import kotlinx.serialization.json.JsonElement

class UnknownJsonTypeException(elem: JsonElement) : BadRequestException("unknown json type $elem")