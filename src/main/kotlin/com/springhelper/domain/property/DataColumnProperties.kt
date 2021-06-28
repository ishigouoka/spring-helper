package com.springhelper.domain.property

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "database-properties.data-column")
data class DataColumnProperties(
    val default: String,
    val booleanColumn: String,
    val generateKey: String,
    val primaryKey: String,
    val columnMap: HashMap<String, String>,
    val enumMap: HashMap<String, String>
)
