package com.springhelper

import com.springhelper.domain.usecase.GenerateUseCase
import org.apache.commons.cli.CommandLine
import org.apache.commons.cli.DefaultParser
import org.apache.commons.cli.Options
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component


@Component
class Runner(
    val generateUseCase: GenerateUseCase
) : CommandLineRunner {
    override fun run(args: Array<String>) {
        val options = Options()
        options.addOption("?", "help", false, "Print this message.")

        val cl: CommandLine = DefaultParser().parse(options, args)

        generateUseCase.generateComponent()

    }
}
