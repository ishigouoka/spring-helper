package com.springhelper.infrastructure.query

import com.springhelper.domain.entity.Column
import com.springhelper.domain.property.DataColumnProperties
import com.springhelper.domain.property.SchemaProperties
import com.springhelper.domain.query.ColumnQuery
import com.springhelper.infrastructure.mapper.MySqlColumnMapper
import com.springhelper.infrastructure.mapper.PostgresColumnMapper
import org.slf4j.Logger
import org.springframework.stereotype.Component

@Component
internal class ColumnQueryImpl(
    private val schemaProperties: SchemaProperties,
    private val dataColumnProperties: DataColumnProperties,
    private val mySqlColumnMapper: MySqlColumnMapper,
    private val postgresColumnMapper: PostgresColumnMapper,
    private val logger: Logger
) : ColumnQuery {

    override fun getMysqlColumnsBySchemaName(
    ): List<Column> {
        return mySqlColumnMapper.findByTableSchema(
            schema = schemaProperties.schemaName,
            excludeTables = schemaProperties.excludeTables
        ).map { it.toEntity(dataColumnProperties) }
    }

    override fun getPostgresColumnsBySchemaName(
    ): List<Column> {
        logger.info(
            "catalog=[{}], schema=[{}]",
            schemaProperties.catalogName,
            schemaProperties.schemaName
        )
        return postgresColumnMapper.findByTableSchema(
            catalog = schemaProperties.catalogName,
            schema = schemaProperties.schemaName,
            excludeTables = schemaProperties.excludeTables
        ).map { it.toEntity(dataColumnProperties) }
    }
}

