package com.springhelper.domain.property

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import javax.xml.catalog.Catalog

@ConstructorBinding
@ConfigurationProperties(prefix = "database-properties.schema")
data class SchemaProperties(
    val schemaName: String,
    val catalogName: String,
    val excludeTables: List<String>
)
