package com.springhelper.infrastructure.mapper.record

import com.springhelper.domain.config.DataColumnMapConfig
import com.springhelper.domain.config.HogeConfig
import com.springhelper.domain.entity.Column

internal data class ColumnRecord(
    val tableName: String,
    val columnName: String,
    val dataType: String,
    val columnType: String,
    val isNullable: String,
    val columnKey: String?
) {

    fun toEntity(
        dataColumnMapConfig: DataColumnMapConfig,
        hogeConfig: HogeConfig
    ): Column {
        return Column(
            tableName = tableName,
            columnName = columnName,
            dataType = dataType,
            columnType = columnType,
            nullable = (isNullable == "yes"),
            columnKey = columnKey,
            objectType = toObjectType(
                dataColumnMapConfig = dataColumnMapConfig,
                hogeConfig = hogeConfig,
                dataType = dataType,
                columnType = columnType
            )
        )
    }

    private fun toObjectType(
        dataColumnMapConfig: DataColumnMapConfig,
        hogeConfig: HogeConfig,
        dataType: String,
        columnType: String,
    ): String {
        return if (hogeConfig.settingMap["booleanColumn"] == columnType) {
            "Boolean"
        } else {
            dataColumnMapConfig.columnMap[dataType]?.let { it } ?: let { "String" }
        }
    }
}
