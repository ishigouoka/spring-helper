package com.springhelper.infrastructure.mapper.record

import com.springhelper.domain.entity.Column
import com.springhelper.domain.helper.StringHelper
import com.springhelper.domain.property.DataColumnProperties

internal data class PostgresColumnRecord(
    val tableName: String,
    val columnName: String,
    val dataType: String,
    val columnType: String,
    val isNullable: String,
    val columnKey: String?,
    val columnDefault: String?
) {

    fun toEntity(
        dataColumnProperties: DataColumnProperties
    ): Column {
        return Column(
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
            isGenerateKey = isGenerateKey()
        )
    }

    private fun toObjectType(
        dataColumnProperties: DataColumnProperties,
    ): String {
        return if (dataColumnProperties.booleanColumn == dataType) {
            "Boolean"
        } else {
            dataColumnProperties.enumMap[columnName]?.let {
                it
            } ?: let {
                dataColumnProperties.columnMap[dataType] ?: let { dataColumnProperties.default }
            }
        }
    }

    private fun isPrimaryKey(
        dataColumnProperties: DataColumnProperties
    ): Boolean {
        return (dataColumnProperties.primaryKey == columnKey)
    }

    private fun isGenerateKey(): Boolean {
        return (columnDefault?.startsWith("nextval")?:false)
    }

    private fun isNullable(
    ): Boolean {
        return (isNullable == "YES")
    }
}
