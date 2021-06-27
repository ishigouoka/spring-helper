package com.springhelper.infrastructure.query

import com.springhelper.domain.entity.Table
import com.springhelper.domain.query.TableQuery
import com.springhelper.infrastructure.mapper.TableMapper
import org.slf4j.Logger
import org.springframework.stereotype.Component

@Component
internal class TableQueryImpl(
    private val tableMapper: TableMapper
) : TableQuery {

    override fun getTables(schema: String): List<Table> {
        return tableMapper.findByTableSchema(schema).map {it.toEntity()}
    }
}

