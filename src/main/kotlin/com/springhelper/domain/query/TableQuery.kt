package com.springhelper.domain.query

import com.springhelper.domain.entity.Table

interface TableQuery {
    fun getMysqlTables(
        schema: String
    ): List<Table>

    fun getPostgresTables(
        schema: String
    ): List<Table>
}
