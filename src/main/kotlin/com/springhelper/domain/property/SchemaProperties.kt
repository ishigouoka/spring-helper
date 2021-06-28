package com.springhelper.domain.property

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "database-properties.schema")
data class SchemaProperties(
    val name: String,
    val excludeTables: List<String>
)
