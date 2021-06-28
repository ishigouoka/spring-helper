package com.springhelper.domain.property

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "spring-helper")
data class SpringHelperProperties(
    val exportPath: String,
    val isCreateRecord: Boolean,
    val isCreateMapper: Boolean,
    val isCreateEntity: Boolean,
    val isCreateQuery: Boolean,
    val isCreateRepository: Boolean
)
