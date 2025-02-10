package com.myeglu.common.kotlinx.serialization

import com.myeglu.common.BadRequestException

class UnknownPrimitiveTypeException(value: Any) : BadRequestException("unknown primitive type $value")