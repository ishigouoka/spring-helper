package com.springhelper

import com.springhelper.domain.usecase.RecordUseCase
import org.apache.commons.cli.Options
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import org.apache.commons.cli.CommandLine

import org.apache.commons.cli.DefaultParser
import org.apache.commons.cli.HelpFormatter
import java.io.StringWriter

import org.apache.velocity.VelocityContext
import org.apache.velocity.app.VelocityEngine
import java.io.File


@Component
class Runner(
    val recordUseCase: RecordUseCase
) : CommandLineRunner {
    override fun run(args: Array<String>) {
        val options = Options()
        options.addOption("?", "help", false, "Print this message.")
        options.addOption("o", "output", true, "Output directory.")

        val cl: CommandLine = DefaultParser().parse(options, args)

        if (cl.hasOption("o")) {
            println("-o: " + cl.getOptionValue("o"))
            when (cl.getOptionValue("o")) {
                "yes" -> recordUseCase.createRecord()
                else -> println("do-nothing")
            }
        }

        if (cl.hasOption("?")) {
            HelpFormatter().printHelp("demo [-o <arg>]", options)
        }


        // --- velocity ---
    }
}