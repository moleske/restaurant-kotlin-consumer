package com.oleske.restaurantkotlinconsumer

import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class RestaurantGetter (val restTemplate: RestTemplate){
    fun getRestaurants(): List<Restaurant> =
            restTemplate.exchange("https://restaurant-kotlin.cfapps.pez.pivotal.io/restaurant", HttpMethod.GET, HttpEntity.EMPTY, object : ParameterizedTypeReference<List<Restaurant>>() {}).body
}