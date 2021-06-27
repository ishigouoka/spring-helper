package com.springhelper.domain.service

import com.springhelper.domain.entity.Column
import org.apache.velocity.VelocityContext
import org.apache.velocity.app.VelocityEngine
import org.springframework.stereotype.Service
import java.io.File
import java.io.StringWriter

@Service
class ColumnService(
    val velocityEngine: VelocityEngine
) {

    fun createRecordClass(
        tableName: String,
        columns: List<Column>
    ) {

        val writer = StringWriter()
        velocityEngine.setProperty("resource.loader", "class")
        velocityEngine.setProperty(
            "class.resource.loader.class",
            "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader"
        )

        velocityEngine.init()

        // --- velocity ---
        val context = VelocityContext()
        context.put("table", tableName)
        context.put("columns", columns)

        columns.forEach {
            println(it.nullable)
        }

        velocityEngine.mergeTemplate("templates/Message.vm", "UTF-8", context, writer)

        val file = File("/Users/kenta/Downloads/spring-helper/output/Hoge.kt")
        file.writeText(writer.toString())
    }
}