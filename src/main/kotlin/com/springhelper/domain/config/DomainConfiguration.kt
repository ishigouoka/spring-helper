package com.springhelper.domain.config

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.BeanCreationException
import org.springframework.beans.factory.InjectionPoint
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope

@Configuration
@ComponentScan("com.springhelper.domain.property")
@ConfigurationPropertiesScan("com.springhelper.domain.property")
class DomainConfiguration {

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    fun logger(ip: InjectionPoint): Logger {
        return LoggerFactory.getLogger(
                ip.methodParameter?.containingClass
                        ?: ip.field?.declaringClass
                        ?: throw BeanCreationException("Cannot findByTemplateId type for Logger. (injection point: ${ip.annotatedElement})")
        )
    }

}
