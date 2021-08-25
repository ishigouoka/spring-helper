package com.springhelper.domain.usecase

import com.springhelper.domain.enum.Dbms
import com.springhelper.domain.helper.VelocityHelper
import com.springhelper.domain.property.DataColumnProperties
import com.springhelper.domain.property.SchemaProperties
import com.springhelper.domain.property.SpringHelperProperties
import com.springhelper.domain.query.ColumnQuery
import com.springhelper.domain.service.ColumnService
import org.apache.velocity.app.VelocityEngine
import org.slf4j.Logger
import org.springframework.stereotype.Component

@Component
class GenerateUseCase(
    val columnQuery: ColumnQuery,
    val columnService: ColumnService,
    val schemaProperties: SchemaProperties,
    val dataColumnProperties: DataColumnProperties,
    val springHelperProperties: SpringHelperProperties,
    val velocityEngine: VelocityEngine,
    val logger: Logger
) {

    fun generateComponent(
        dbms: Dbms?
    ) {

        logger.info("Generate Component DBMS=[{}]", dbms)

        val columns = when (dbms) {
            Dbms.POSTGRES -> {
                columnQuery.getPostgresColumnsBySchemaName()
            }
            else -> {
                columnQuery.getMysqlColumnsBySchemaName()
            }
        }

        val columnsGroupByTableName = columns.groupBy { it.tableName }

        VelocityHelper.initVelocityEngine(velocityEngine)

        // generate record
        if (springHelperProperties.isCreateRecord) {
            logger.info("Generate Record START.")
            columnsGroupByTableName.forEach { (tableName, columns) ->
                columnService.createRecordClass(
                    tableName = tableName,
                    columns = columns,
                    velocityEngine = velocityEngine
                )
                logger.info("Generate Record TABLE=[{}]", tableName)
            }
            logger.info("Generate Record END.")
        }

        // generate mapper
        if (springHelperProperties.isCreateMapper) {
            logger.info("Generate Mapper START.")
            columnsGroupByTableName.forEach { (tableName, columns) ->

                columnService.createMapperClass(
                    tableName = tableName,
                    columns = columns,
                    velocityEngine = velocityEngine
                )
                logger.info("Generate Mapper TABLE=[{}]", tableName)
            }
            logger.info("Generate Mapper END.")
        }

        // generate entity
        columnsGroupByTableName.forEach { (tableName, columns) ->
            if (springHelperProperties.isCreateEntity) {
                columnService.createEntitiesClass(
                    tableName = tableName,
                    columns = columns,
                    velocityEngine = velocityEngine
                )
            }
        }

        // generate query
        columnsGroupByTableName.forEach { (tableName, columns) ->
            if (springHelperProperties.isCreateQuery) {
                columnService.createQueryInterface(
                    tableName = tableName,
                    columns = columns,
                    velocityEngine = velocityEngine
                )
            }
        }

        // generate repository
        columnsGroupByTableName.forEach { (tableName, columns) ->
            if (springHelperProperties.isCreateRepository) {
                columnService.createRepositoryInterface(
                    tableName = tableName,
                    columns = columns,
                    velocityEngine = velocityEngine
                )
            }
        }
    }
}
