package com.pientaa.shoppingplatform

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class ShoppingPlatformApplication

fun main(args: Array<String>) {
    runApplication<ShoppingPlatformApplication>(*args)
}
