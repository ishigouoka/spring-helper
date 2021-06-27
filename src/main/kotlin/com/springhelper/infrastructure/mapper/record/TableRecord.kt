package com.springhelper.infrastructure.mapper.record

import com.springhelper.domain.entity.Table

//@NoArg
internal data class TableRecord(
    val tableName: String
) {

    fun toEntity(): Table {
        return Table(
            tableName = tableName
        )
    }
}
