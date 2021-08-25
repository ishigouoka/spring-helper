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
    val springHelperProperties: SpringHelperProperties,
    val velocityEngine: VelocityEngine,
    val logger: Logger
) {

    fun generateComponent(
        dbms: Dbms?
    ) {

        columnService.clearOutputPath()

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
        if (springHelperProperties.isCreateEntity) {
            logger.info("Generate Entity START.")
            columnsGroupByTableName.forEach { (tableName, columns) ->
                columnService.createEntitiesClass(
                    tableName = tableName,
                    columns = columns,
                    velocityEngine = velocityEngine
                )
                logger.info("Generate Entity TABLE=[{}]", tableName)
            }
            logger.info("Generate Entity END.")
        }

        // generate query
        if (springHelperProperties.isCreateQuery) {
            logger.info("Generate Query START.")
            columnsGroupByTableName.forEach { (tableName, columns) ->

                columnService.createQueryInterface(
                    tableName = tableName,
                    columns = columns,
                    velocityEngine = velocityEngine
                )
                logger.info("Generate Query TABLE=[{}]", tableName)
            }
            logger.info("Generate Query END.")
        }

        // generate repository
        if (springHelperProperties.isCreateRepository) {
            columnsGroupByTableName.forEach { (tableName, columns) ->
                logger.info("Generate Repository START.")
                columnService.createRepositoryInterface(
                    tableName = tableName,
                    columns = columns,
                    velocityEngine = velocityEngine
                )
                logger.info("Generate Repository TABLE=[{}]", tableName)
            }
            logger.info("Generate Repository END.")
        }
    }
}
