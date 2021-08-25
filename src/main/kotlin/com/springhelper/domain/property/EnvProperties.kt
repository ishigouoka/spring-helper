package com.springhelper.domain.property

import com.springhelper.domain.enum.Dbms
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "env")
data class EnvProperties(
    val dbms: Dbms
)
