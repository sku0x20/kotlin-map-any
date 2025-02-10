package com.myeglu.common.kotlinx.serialization

import com.myeglu.common.BadRequestException
import kotlinx.serialization.json.JsonElement

class UnknownJsonTypeException(elem: JsonElement) : BadRequestException("unknown json type $elem")