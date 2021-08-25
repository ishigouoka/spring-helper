package com.springhelper.infrastructure.mapper

import com.springhelper.infrastructure.mapper.record.PostgresColumnRecord
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

@Mapper
internal interface PostgresColumnMapper {

    @Select(
        """
        <script>
            SELECT
                columns.table_name,
                columns.column_name,
                columns.udt_name as data_type,
                columns.is_nullable,
                columns.udt_name as column_type,
                temp.constraint_type as column_key,
                columns.column_default
            FROM information_schema.columns columns
            LEFT JOIN 
                (
                    SELECT ccu.column_name, ccu.table_name, tc.constraint_type
                    FROM information_schema.table_constraints tc
                    INNER JOIN information_schema.constraint_column_usage ccu
                    ON (
                        tc.table_catalog=ccu.table_catalog
                        AND tc.table_schema=ccu.table_schema
                        AND tc.table_name=ccu.table_name
                        AND tc.constraint_name=ccu.constraint_name
                    )
                ) temp
            ON columns.table_name = temp.table_name AND columns.column_name = temp.column_name
            WHERE columns.table_catalog = #{catalog}
            AND columns.table_schema = #{schema} 
            AND columns.table_name NOT IN
            <foreach item='excludeTable' collection='excludeTables'
                open='(' separator=',' close=')'>
                #{excludeTable}
            </foreach>
        </script>
    """
    )
    fun findByTableSchema(
        @Param("catalog") catalog: String,
        @Param("schema") schema: String,
        @Param("excludeTables") excludeTables: List<String>
    ): List<PostgresColumnRecord>
}

