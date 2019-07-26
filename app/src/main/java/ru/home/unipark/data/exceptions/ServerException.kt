package ru.home.unipark.data.exceptions

import java.lang.Exception

class ServerException(
    val code: Int,
    override val message: String
): Exception()