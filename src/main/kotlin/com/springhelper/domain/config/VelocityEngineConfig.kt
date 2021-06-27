package com.springhelper.domain.config

import org.apache.velocity.app.VelocityEngine
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class VelocityEngineConfig {

    @Bean
    fun getVelocityEngine(): VelocityEngine? {
        return VelocityEngine()
    }
}