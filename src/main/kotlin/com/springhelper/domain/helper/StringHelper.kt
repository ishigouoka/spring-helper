package com.springhelper.domain.helper

import java.util.*

object StringHelper {

    fun toUpperCamelCase(arg: String): String {
        return arg.lowercase().toCamelCase().beginWithUpperCase()
    }

    fun toLowerCamelCase(arg: String): String {
        return arg.lowercase().toCamelCase().beginWithLowerCase()
    }

    fun toUpperSnakeCase(arg: String): String {
        return arg.toSnakeCase().beginWithUpperCase()
    }

    fun toLowerSnakeCase(arg: String): String {
        return arg.toSnakeCase().beginWithLowerCase()
    }

    private fun String.beginWithLowerCase(): String {
        return when (this.length) {
            0 -> ""
            1 -> this.lowercase(Locale.getDefault())
            else -> this[0].lowercaseChar() + this.substring(1)
        }
    }

    private fun String.beginWithUpperCase(): String {
        return when (this.length) {
            0 -> ""
            1 -> this.uppercase(Locale.getDefault())
            else -> this[0].uppercaseChar() + this.substring(1)
        }
    }

    private fun String.toCamelCase(): String {
        return this.split('_').map {
            it.beginWithUpperCase() } .joinToString("")
    }

    private fun String.toSnakeCase(): String {
        var text: String = ""
        var isFirst = true
        this.forEach {
            if (it.isUpperCase()) {
                if (isFirst) isFirst = false
                else text += "_"
                text += it.lowercaseChar()
            } else {
                text += it
            }
        }
        return text
    }
}
