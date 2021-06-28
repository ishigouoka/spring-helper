package com.springhelper.infrastructure.mapper.record

import com.springhelper.domain.entity.MySqlColumn
import com.springhelper.domain.helper.StringHelper
import com.springhelper.domain.property.DataColumnProperties

internal data class MySqlColumnRecord(
    val tableName: String,
    val columnName: String,
    val dataType: String,
    val columnType: String,
    val isNullable: String,
    val columnKey: String?,
    val extra: String?
) {

    fun toEntity(
        dataColumnProperties: DataColumnProperties
    ): MySqlColumn {
        return MySqlColumn(
            tableName = tableName,
            columnName = columnName,
            camelCaseColumnName = StringHelper.toLowerCamelCase(columnName),
            dataType = dataType,
            columnType = columnType,
            isNullable = isNullable(),
            objectType = toObjectType(
                dataColumnProperties = dataColumnProperties
            ),
            isPrimaryKey = isPrimaryKey(dataColumnProperties = dataColumnProperties),
            isGenerateKey = isGenerateKey(dataColumnProperties = dataColumnProperties)
        )
    }

    private fun toObjectType(
        dataColumnProperties: DataColumnProperties,
    ): String {
        return if (dataColumnProperties.booleanColumn == columnType) {
            "Boolean"
        } else {
            dataColumnProperties.enumMap[columnName]?.let {
                it
            } ?: let {
                dataColumnProperties.columnMap[dataType] ?: let { dataColumnProperties.default }
            } ?: let { dataColumnProperties.default }
        }
    }

    private fun isPrimaryKey(
        dataColumnProperties: DataColumnProperties
    ): Boolean {
        return (dataColumnProperties.primaryKey == columnKey)
    }

    private fun isGenerateKey(
        dataColumnProperties: DataColumnProperties
    ): Boolean {
        return (dataColumnProperties.generateKey == extra)
    }

    private fun isNullable(
    ): Boolean {
        return (isNullable == "YES")
    }
}
