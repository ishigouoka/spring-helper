package com.springhelper.domain.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "data-column-map")
class DataColumnMapConfig(
//    val default: String,
//    @Value("booleanColumn")
//    val booleanColumn: String,
    val columnMap: HashMap<String, String>
)