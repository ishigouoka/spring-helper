package com.springhelper.domain.entity

data class Column(
    val tableName: String,
    val columnName: String,
    val dataType: String,
    val columnType: String,
    val nullable: Boolean,
    val columnKey: String?,
    val objectType: String
)
