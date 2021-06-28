package com.springhelper.domain.entity

data class MySqlColumn(
    val tableName: String,
    val columnName: String,
    val camelCaseColumnName: String,
    val dataType: String,
    val columnType: String,
    val isNullable: Boolean,
    val objectType: String,
    val isGenerateKey: Boolean,
    val isPrimaryKey: Boolean
)
