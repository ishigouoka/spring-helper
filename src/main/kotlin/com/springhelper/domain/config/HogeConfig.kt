package com.springhelper.domain.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "data-column")
class HogeConfig(

    val settingMap: HashMap<String, String>
)