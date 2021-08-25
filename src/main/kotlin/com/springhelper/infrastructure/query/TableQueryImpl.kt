package com.springhelper.infrastructure.query

import com.springhelper.domain.entity.Table
import com.springhelper.domain.query.TableQuery
import com.springhelper.infrastructure.mapper.TableMapper
import org.springframework.stereotype.Component

@Component
internal class TableQueryImpl(
    private val tableMapper: TableMapper
) : TableQuery {

    override fun getMysqlTables(schema: String): List<Table> {
        return tableMapper.findByMysqlTableSchema(schema).map {it.toEntity()}
    }

    override fun getPostgresTables(schema: String): List<Table> {
        return tableMapper.findByPostgresTableSchema(schema).map {it.toEntity()}
    }
}

