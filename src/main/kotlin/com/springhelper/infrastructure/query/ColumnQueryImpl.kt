package com.springhelper.infrastructure.query

import com.springhelper.domain.config.DataColumnMapConfig
import com.springhelper.domain.config.HogeConfig
import com.springhelper.domain.entity.Column
import com.springhelper.domain.query.ColumnQuery
import com.springhelper.infrastructure.mapper.ColumnMapper
import org.springframework.stereotype.Component

@Component
internal class ColumnQueryImpl(
    private val columnMapper: ColumnMapper
) : ColumnQuery {

    override fun getColumnsByTableName(
        tableName: String,
        dataColumnMapConfig: DataColumnMapConfig,
        hogeConfig: HogeConfig
    ): List<Column> {
        return columnMapper.findByTableName(tableName).map { it.toEntity(dataColumnMapConfig, hogeConfig) }
    }

    override fun getColumnsBySchemaName(
        schema: String,
        dataColumnMapConfig: DataColumnMapConfig,
        hogeConfig: HogeConfig
    ): List<Column> {
        return columnMapper.findByTableSchema(schema).map { it.toEntity(dataColumnMapConfig, hogeConfig) }
    }
}

