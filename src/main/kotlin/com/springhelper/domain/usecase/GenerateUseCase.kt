package com.springhelper.domain.usecase

import com.springhelper.domain.helper.VelocityHelper
import com.springhelper.domain.property.DataColumnProperties
import com.springhelper.domain.property.SchemaProperties
import com.springhelper.domain.property.SpringHelperProperties
import com.springhelper.domain.query.ColumnQuery
import com.springhelper.domain.service.ColumnService
import org.apache.velocity.app.VelocityEngine
import org.springframework.stereotype.Component

@Component
class GenerateUseCase(
    val columnQuery: ColumnQuery,
    val columnService: ColumnService,
    val schemaProperties: SchemaProperties,
    val dataColumnProperties: DataColumnProperties,
    val springHelperProperties: SpringHelperProperties,
    val velocityEngine: VelocityEngine
) {

    fun generateComponent() {

        val columnsGroupByTableName = columnQuery.getColumnsBySchemaName(
            schema = schemaProperties.name,
            dataColumnProperties = dataColumnProperties
        ).groupBy { it.tableName }

        VelocityHelper.initVelocityEngine(velocityEngine)

        // generate record
        columnsGroupByTableName.forEach { (tableName, columns) ->
            if (springHelperProperties.isCreateRecord) {
                columnService.createRecordClass(
                    tableName = tableName,
                    mySqlColumns = columns,
                    velocityEngine = velocityEngine
                )
            }
        }

        // generate mapper
        columnsGroupByTableName.forEach { (tableName, columns) ->
            if (springHelperProperties.isCreateMapper) {
                columnService.createMapperClass(
                    tableName = tableName,
                    mySqlColumns = columns,
                    velocityEngine = velocityEngine
                )
            }
        }

        // generate entity
        columnsGroupByTableName.forEach { (tableName, columns) ->
            if (springHelperProperties.isCreateEntity) {
                columnService.createEntitiesClass(
                    tableName = tableName,
                    mySqlColumns = columns,
                    velocityEngine = velocityEngine
                )
            }
        }

        // generate query
        columnsGroupByTableName.forEach { (tableName, columns) ->
            if (springHelperProperties.isCreateQuery) {
                columnService.createQueryInterface(
                    tableName = tableName,
                    mySqlColumns = columns,
                    velocityEngine = velocityEngine
                )
            }
        }

        // generate repository
        columnsGroupByTableName.forEach { (tableName, columns) ->
            if (springHelperProperties.isCreateRepository) {
                columnService.createRepositoryInterface(
                    tableName = tableName,
                    mySqlColumns = columns,
                    velocityEngine = velocityEngine
                )
            }
        }
    }
}
