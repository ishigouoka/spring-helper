package com.springhelper

import com.springhelper.domain.enum.Dbms
import com.springhelper.domain.property.EnvProperties
import com.springhelper.domain.usecase.GenerateUseCase
import org.apache.commons.cli.CommandLine
import org.apache.commons.cli.DefaultParser
import org.apache.commons.cli.Options
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component


@Component
class Runner(
    val generateUseCase: GenerateUseCase,
    val envProperties: EnvProperties
) : CommandLineRunner {
    override fun run(args: Array<String>) {
        val options = Options()
        options.addOption("?", "help", false, "Print this message.")

//        val cl: CommandLine = DefaultParser().parse(options, args)
        val dbms = when(envProperties.dbms) {
            Dbms.POSTGRES -> Dbms.POSTGRES
            else -> Dbms.MYSQL
        }

        generateUseCase.generateComponent(dbms)
    }
}
