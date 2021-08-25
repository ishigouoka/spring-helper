package com.springhelper.domain.service

import com.springhelper.domain.entity.Column
import com.springhelper.domain.helper.StringHelper
import com.springhelper.domain.property.ExportClassProperties
import com.springhelper.domain.property.SpringHelperProperties
import org.apache.velocity.VelocityContext
import org.apache.velocity.app.VelocityEngine
import org.slf4j.Logger
import org.springframework.stereotype.Service
import java.io.File
import java.io.StringWriter
import java.net.URI
import java.nio.file.Files
import java.nio.file.Path

@Service
class ColumnService(
    val springHelperProperties: SpringHelperProperties,
    val exportClassProperties: ExportClassProperties,
    val logger: Logger
) {

    fun clearOutputPath() {
        logger.info("Clear Directory. Path=[{}]", springHelperProperties.exportPath)
        val outputDirectory = File(springHelperProperties.exportPath)
        File(springHelperProperties.exportPath).deleteRecursively()
        Files.createDirectory(Path.of(springHelperProperties.exportPath))
    }

    fun createRecordClass(
        tableName: String,
        columns: List<Column>,
        velocityEngine: VelocityEngine
    ) {
        val context = initCommonContext()
        val camelCaseTableName = StringHelper.toUpperCamelCase(tableName)
        context.put("table", camelCaseTableName)
        context.put("columns", columns)
        context.put("packageName", exportClassProperties.recordClass.packageName)
        context.put("classSuffix", exportClassProperties.recordClass.suffix)
        context.put("entityPackageName", exportClassProperties.entityClass.packageName)
        context.put("entityClassName", "${camelCaseTableName}${exportClassProperties.entityClass.suffix}")

        val writer = StringWriter()
        velocityEngine.mergeTemplate(
            exportClassProperties.recordClass.templatePath,
            exportClassProperties.recordClass.encoding,
            context,
            writer
        )

        val outputPath = "${springHelperProperties.exportPath}${exportClassProperties.recordClass.exportPath}"
        if (!File(outputPath).exists()) {
            Files.createDirectory(Path.of(outputPath))
        }
        val file =
            File("${outputPath}/${camelCaseTableName}${exportClassProperties.recordClass.suffix}${exportClassProperties.recordClass.extension}")
        file.writeText(writer.toString())
        writer.close()
    }

    fun createMapperClass(
        tableName: String,
        columns: List<Column>,
        velocityEngine: VelocityEngine
    ) {
        val context = initCommonContext()
        val camelCaseTableName = StringHelper.toUpperCamelCase(tableName)
        context.put("tableName", tableName)
        context.put("camelCaseTableName", camelCaseTableName)
        context.put("columns", columns)
        context.put("packageName", exportClassProperties.mapperClass.packageName)
        context.put("classSuffix", exportClassProperties.mapperClass.suffix)

        context.put("recordPackageName", exportClassProperties.recordClass.packageName)
        context.put("recordClassName", "${camelCaseTableName}${exportClassProperties.recordClass.suffix}")

        val writer = StringWriter()
        velocityEngine.mergeTemplate(
            exportClassProperties.mapperClass.templatePath,
            exportClassProperties.mapperClass.encoding,
            context,
            writer
        )

        val outputPath = "${springHelperProperties.exportPath}${exportClassProperties.mapperClass.exportPath}"
        if (!File(outputPath).exists()) {
            Files.createDirectory(Path.of(outputPath))
        }
        val file =
            File("${outputPath}/${camelCaseTableName}${exportClassProperties.mapperClass.suffix}${exportClassProperties.mapperClass.extension}")
        file.writeText(writer.toString())
        writer.close()
    }

    fun createEntitiesClass(
        tableName: String,
        columns: List<Column>,
        velocityEngine: VelocityEngine
    ) {
        val context = initCommonContext()
        val camelCaseTableName = StringHelper.toUpperCamelCase(tableName)
        context.put("table", camelCaseTableName)
        context.put("columns", columns)
        context.put("packageName", exportClassProperties.entityClass.packageName)
        context.put("classSuffix", exportClassProperties.entityClass.suffix)

        val writer = StringWriter()
        velocityEngine.mergeTemplate(
            exportClassProperties.entityClass.templatePath,
            exportClassProperties.entityClass.encoding,
            context,
            writer
        )

        val outputPath = "${springHelperProperties.exportPath}${exportClassProperties.entityClass.exportPath}"
        if (!File(outputPath).exists()) {
            Files.createDirectory(Path.of(outputPath))
        }
        val file =
            File("${outputPath}/${camelCaseTableName}${exportClassProperties.entityClass.suffix}${exportClassProperties.entityClass.extension}")
        file.writeText(writer.toString())
        writer.close()
    }

    fun createQueryInterface(
        tableName: String,
        columns: List<Column>,
        velocityEngine: VelocityEngine
    ) {
        val context = initCommonContext()
        val camelCaseTableName = StringHelper.toUpperCamelCase(tableName)
        context.put("table", camelCaseTableName)
        context.put("columns", columns)
        context.put("packageName", exportClassProperties.queryInterface.packageName)
        context.put("camelCaseTableName", StringHelper.toUpperCamelCase(tableName))
        context.put("classSuffix", exportClassProperties.queryInterface.suffix)

        context.put("entityPackageName", exportClassProperties.entityClass.packageName)
        context.put(
            "entityClassName",
            "${StringHelper.toUpperCamelCase(tableName)}${exportClassProperties.entityClass.suffix}"
        )

        val writer = StringWriter()
        velocityEngine.mergeTemplate(
            exportClassProperties.queryInterface.templatePath,
            exportClassProperties.queryInterface.encoding,
            context,
            writer
        )

        val outputPath = "${springHelperProperties.exportPath}${exportClassProperties.queryInterface.exportPath}"
        if (!File(outputPath).exists()) {
            Files.createDirectory(Path.of(outputPath))
        }
        val file =
            File("${outputPath}/${camelCaseTableName}${exportClassProperties.queryInterface.suffix}${exportClassProperties.queryInterface.extension}")
        file.writeText(writer.toString())
        writer.close()
    }

    fun createRepositoryInterface(
        tableName: String,
        columns: List<Column>,
        velocityEngine: VelocityEngine
    ) {
        val context = initCommonContext()
        val camelCaseTableName = StringHelper.toUpperCamelCase(tableName)
        context.put("table", camelCaseTableName)
        context.put("columns", columns)
        context.put("packageName", exportClassProperties.repositoryInterface.packageName)
        context.put("camelCaseTableName", camelCaseTableName)
        context.put("classSuffix", exportClassProperties.repositoryInterface.suffix)

        context.put("entityPackageName", exportClassProperties.entityClass.packageName)
        context.put("entityClassName", "${camelCaseTableName}${exportClassProperties.entityClass.suffix}")

        val writer = StringWriter()
        velocityEngine.mergeTemplate(
            exportClassProperties.repositoryInterface.templatePath,
            exportClassProperties.repositoryInterface.encoding,
            context,
            writer
        )

        val outputPath = "${springHelperProperties.exportPath}${exportClassProperties.repositoryInterface.exportPath}"
        if (!File(outputPath).exists()) {
            Files.createDirectory(Path.of(outputPath))
        }
        val file =
            File("${outputPath}/${camelCaseTableName}${exportClassProperties.repositoryInterface.suffix}${exportClassProperties.repositoryInterface.extension}")
        file.writeText(writer.toString())
        writer.close()
    }

    private fun initCommonContext(): VelocityContext {
        val context = VelocityContext()
        context.put("newline", "\n")
        context.put("bindPrefix", "#{")
        context.put("bindSuffix", "}")
        return context
    }
}
