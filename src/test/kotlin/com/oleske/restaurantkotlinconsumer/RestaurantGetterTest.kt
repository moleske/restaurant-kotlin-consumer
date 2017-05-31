package com.oleske.restaurantkotlinconsumer

import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Matchers.any
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.runners.MockitoJUnitRunner
import org.springframework.web.client.RestTemplate
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity


@RunWith(MockitoJUnitRunner::class)
class RestaurantGetterTest {
    @Mock
    lateinit var restTemplate: RestTemplate

    lateinit var subject: RestaurantGetter

    @Before
    fun setUp() {
        subject = RestaurantGetter(restTemplate)
    }

    @Test
    fun getRestaurants() {
        val results = listOf(Restaurant(name = Math.random().toString()), Restaurant(name = Math.random().toString()))
        `when`(restTemplate.exchange(any(String::class.java), any(HttpMethod::class.java), any(HttpEntity::class.java), any(ParameterizedTypeReference::class.java))).thenReturn(ResponseEntity(results, HttpStatus.OK))

        val restaurants = subject.getRestaurants()

        verify(restTemplate).exchange("https://restaurant-kotlin.cfapps.pez.pivotal.io/restaurant", HttpMethod.GET, null, object : ParameterizedTypeReference<List<Restaurant>>() {
        })

        assertThat(restaurants).isEqualTo(results)
    }

}