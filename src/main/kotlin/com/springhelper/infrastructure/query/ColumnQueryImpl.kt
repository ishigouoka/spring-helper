package com.springhelper.infrastructure.query

import com.springhelper.domain.entity.MySqlColumn
import com.springhelper.domain.property.DataColumnProperties
import com.springhelper.domain.property.SchemaProperties
import com.springhelper.domain.query.ColumnQuery
import com.springhelper.infrastructure.mapper.MySqlColumnMapper
import org.springframework.stereotype.Component

@Component
internal class ColumnQueryImpl(
    private val schemaProperties: SchemaProperties,
    private val mySqlColumnMapper: MySqlColumnMapper
) : ColumnQuery {

    override fun getColumnsBySchemaName(
        schema: String,
        dataColumnProperties: DataColumnProperties
    ): List<MySqlColumn> {
        return mySqlColumnMapper.findByTableSchema(
            schema = schema,
            excludeTables = schemaProperties.excludeTables
        ).map { it.toEntity(dataColumnProperties) }
    }
}

