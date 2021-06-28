package com.springhelper.domain.helper

import org.apache.velocity.app.VelocityEngine

class VelocityHelper {
    companion object {
        fun initVelocityEngine(velocityEngine: VelocityEngine) {

            velocityEngine.setProperty("resource.loader", "class")
            velocityEngine.setProperty(
                "class.resource.loader.class",
                "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader"
            )

            velocityEngine.init()
        }
    }
}
