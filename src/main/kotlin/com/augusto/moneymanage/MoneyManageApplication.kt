package com.augusto.moneymanage

import com.augusto.moneymanage.config.WebConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@SpringBootApplication
class MoneyManageApplication

fun main(args: Array<String>) {
	runApplication<MoneyManageApplication>(*args)
}
