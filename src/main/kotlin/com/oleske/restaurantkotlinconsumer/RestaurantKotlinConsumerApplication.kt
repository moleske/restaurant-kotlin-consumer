package com.oleske.restaurantkotlinconsumer

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class RestaurantKotlinConsumerApplication

fun main(args: Array<String>) {
    SpringApplication.run(RestaurantKotlinConsumerApplication::class.java, *args)
}
