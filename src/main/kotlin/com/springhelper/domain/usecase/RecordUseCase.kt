package com.springhelper.domain.usecase

import com.springhelper.domain.config.DataColumnMapConfig
import com.springhelper.domain.config.HogeConfig
import com.springhelper.domain.helper.StringHelper
import com.springhelper.domain.query.ColumnQuery
import com.springhelper.domain.query.TableQuery
import com.springhelper.domain.service.ColumnService
import org.springframework.stereotype.Component

@Component
class RecordUseCase(
//    val tableQuery: TableQuery,
    val columnQuery: ColumnQuery,
    val columnService: ColumnService,

    val dataColumnMapConfig: DataColumnMapConfig,
    val hogeConfig: HogeConfig
) {

    fun createRecord() {
//        val tables = tableQuery.getTables("slave")

        val columnsGroupByTableName = columnQuery.getColumnsBySchemaName("slave", dataColumnMapConfig, hogeConfig).groupBy { it.tableName }

        val tableName = "project"
        columnService.createRecordClass(
            tableName = tableName,
            columns = columnsGroupByTableName[tableName]?:emptyList()
        )
//        println("hoge: " + dataColumnMapConfig.booleanColumn)
//        dataColumnMapConfig.data.entries.forEach {
//            println(it.key + " : " + it.value)
//        }


    }
}