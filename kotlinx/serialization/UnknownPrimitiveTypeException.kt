package com.exmaple.kotlinx.serialization

import com.exmaple.BadRequestException

class UnknownPrimitiveTypeException(value: Any) : BadRequestException("unknown primitive type $value")