package com.springhelper.infrastructure.mapper

import com.springhelper.infrastructure.mapper.record.TableRecord
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select

@Mapper
internal interface TableMapper {

    @Select("""
        SELECT
            `TABLE_NAME`
        FROM `TABLES` 
        WHERE `TABLE_SCHEMA` = #{schema}
    """)
    fun findByMysqlTableSchema(schema: String): List<TableRecord>

    @Select("""
        SELECT
            tablename as table_name
        FROM pg_tables 
        WHERE schemaname = #{schema}
    """)
    fun findByPostgresTableSchema(schema: String): List<TableRecord>
}

