package com.springhelper

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@EnableConfigurationProperties
@SpringBootApplication
class SpringHelperApplication

fun main(args: Array<String>) {
	runApplication<SpringHelperApplication>(*args)
}
