package com.example.kotlinx.serialization

import com.example.BadRequestException

class UnknownPrimitiveTypeException(value: Any) : BadRequestException("unknown primitive type $value")