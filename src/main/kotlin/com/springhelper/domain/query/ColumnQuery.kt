package com.springhelper.domain.query

import com.springhelper.domain.entity.Column
import com.springhelper.domain.property.DataColumnProperties

interface ColumnQuery {
    fun getMysqlColumnsBySchemaName(): List<Column>

    fun getPostgresColumnsBySchemaName(): List<Column>
}
