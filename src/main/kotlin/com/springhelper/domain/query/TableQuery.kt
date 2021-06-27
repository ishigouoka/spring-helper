package com.springhelper.domain.query

import com.springhelper.domain.entity.Table

interface TableQuery {
    fun getTables(
        schema: String
    ): List<Table>
}