package com.springhelper.domain.query

import com.springhelper.domain.entity.MySqlColumn
import com.springhelper.domain.property.DataColumnProperties

interface ColumnQuery {
    fun getColumnsBySchemaName(
        schema: String,
        dataColumnProperties: DataColumnProperties
    ): List<MySqlColumn>
}
