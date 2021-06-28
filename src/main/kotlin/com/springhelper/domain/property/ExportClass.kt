package com.springhelper.domain.property

data class ExportClass(
    val templatePath: String,
    val encoding: String,
    val suffix: String?,
    val extension: String?,
    val packageName: String?,
    val exportPath: String?
)
