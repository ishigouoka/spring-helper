package com.springhelper.infrastructure.mapper

import com.springhelper.infrastructure.mapper.record.MySqlColumnRecord
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select

@Mapper
internal interface MySqlColumnMapper {

    companion object {
        private const val SELECT_COLUMNS = """
            `TABLE_NAME`,
            `COLUMN_NAME`,
            `DATA_TYPE`,
            `COLUMN_TYPE`,
            `COLUMN_KEY`,
            `IS_NULLABLE`,
            `EXTRA`
        """
    }

    @Select("""
        <script>
            SELECT
                $SELECT_COLUMNS 
            FROM `COLUMNS` 
            WHERE `TABLE_SCHEMA` = #{schema}
            AND `TABLE_NAME` NOT IN
            <foreach item='excludeTable' collection='excludeTables'
                open='(' separator=',' close=')'>
                #{excludeTable}
            </foreach>
        </script>
    """)
    fun findByTableSchema(schema: String, excludeTables: List<String>): List<MySqlColumnRecord>
}

