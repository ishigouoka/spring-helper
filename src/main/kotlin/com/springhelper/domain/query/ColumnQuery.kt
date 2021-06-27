package com.springhelper.domain.query

import com.springhelper.domain.config.DataColumnMapConfig
import com.springhelper.domain.config.HogeConfig
import com.springhelper.domain.entity.Column

interface ColumnQuery {
    fun getColumnsByTableName(
        tableName: String,
        dataColumnMapConfig: DataColumnMapConfig,
        hogeConfig: HogeConfig
    ): List<Column>

    fun getColumnsBySchemaName(
        schema: String,
        dataColumnMapConfig: DataColumnMapConfig,
        hogeConfig: HogeConfig
    ): List<Column>
}