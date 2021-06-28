package com.springhelper.domain.property

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "export-class")
data class ExportClassProperties(
    val recordClass: ExportClass,
    val mapperClass: ExportClass,
    val entityClass: ExportClass,
    val queryInterface: ExportClass,
    val repositoryInterface: ExportClass,
)
