package com.springhelper.domain.helper

object StringHelper {

    fun toUpperCamelCase(arg: String): String {
        return arg.toCamelCase().beginWithUpperCase()
    }

    fun toLowerCamelCase(arg: String): String {
        return arg.toCamelCase().beginWithLowerCase()
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
            1 -> this.toLowerCase()
            else -> this[0].toLowerCase() + this.substring(1)
        }
    }

    private fun String.beginWithUpperCase(): String {
        return when (this.length) {
            0 -> ""
            1 -> this.toUpperCase()
            else -> this[0].toUpperCase() + this.substring(1)
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
                text += it.toLowerCase()
            } else {
                text += it
            }
        }
        return text
    }
}