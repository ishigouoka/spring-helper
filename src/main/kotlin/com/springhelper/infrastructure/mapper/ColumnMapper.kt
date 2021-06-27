package com.springhelper.infrastructure.mapper

import com.springhelper.infrastructure.mapper.record.ColumnRecord
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select

@Mapper
internal interface ColumnMapper {

    @Select("""
        SELECT
            `TABLE_NAME`,
            `COLUMN_NAME`,
            `DATA_TYPE`,
            `COLUMN_TYPE`,
            `COLUMN_KEY`,
            `IS_NULLABLE`
        FROM `COLUMNS` 
        WHERE `TABLE_NAME` = #{tableName}
    """)
    fun findByTableName(tableName: String): List<ColumnRecord>

    @Select("""
        SELECT
            `TABLE_NAME`,
            `COLUMN_NAME`,
            `DATA_TYPE`,
            `COLUMN_TYPE`,
            `COLUMN_KEY`,
            `IS_NULLABLE`
        FROM `COLUMNS` 
        WHERE `TABLE_SCHEMA` = #{schema}
    """)
    fun findByTableSchema(schema: String): List<ColumnRecord>
}

