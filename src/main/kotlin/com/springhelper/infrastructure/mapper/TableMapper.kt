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
    fun findByTableSchema(schema: String): List<TableRecord>
}

