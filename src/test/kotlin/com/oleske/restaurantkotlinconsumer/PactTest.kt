package com.oleske.restaurantkotlinconsumer

import au.com.dius.pact.consumer.Pact
import au.com.dius.pact.consumer.PactProviderRule
import au.com.dius.pact.consumer.PactVerification
import au.com.dius.pact.consumer.dsl.PactDslJsonArray
import au.com.dius.pact.consumer.dsl.PactDslWithProvider
import au.com.dius.pact.model.PactFragment
import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.test.context.junit4.SpringRunner

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner::class)
class PactTest {
    @Autowired
    val testRestTemplate: TestRestTemplate? = null

    @get:Rule
    val mockProvider = PactProviderRule("restaurant_api", this)

    @Pact(consumer = "restaurant_consumer")
    fun createGetRestaurantFragment(builder: PactDslWithProvider): PactFragment {
        return builder
                .given("there are available restaurants")
                .uponReceiving("restaurant list request")
                .path("/restaurant")
                .method("GET")
                .willRespondWith()
                .status(200)
                .headers(object : HashMap<String, String>() {
                    init {
                        put("Content-Type", "application/json")
                    }
                })
                .body(PactDslJsonArray
                        .arrayMinLike(0, 1)
                        .id("id")
                        .stringMatcher("name", ".*")
                        .stringMatcher("ownerName", ".*")
                        .stringMatcher("headChefName", ".*")
                        .stringMatcher("cuisineType", ".*")
                        .stringMatcher("shortDescription", ".*")
                        .stringMatcher("fullDescription", ".*")
                        .stringMatcher("websiteUrl", ".*")
                        .integerType("rating")
                        .integerType("michelinStarRating")
                        .integerType("zagatRating"))
                .toFragment()
    }

    @Test
    @PactVerification(fragment = "createGetRestaurantFragment")
    @Throws(Exception::class)
    fun getRestaurantContract() {
        val entity = testRestTemplate!!.exchange<List<Restaurant>>(
                mockProvider.config.url() + "/restaurant",
                HttpMethod.GET,
                null,
                object : ParameterizedTypeReference<List<Restaurant>>() {

                })
        assertThat(entity.statusCodeValue).isEqualTo(200)
        assertThat(entity.body?.size).isGreaterThan(0)
        val restaurant = entity.body?.get(0)
        assertThat(restaurant?.id).isGreaterThanOrEqualTo(0)
        assertThat(restaurant?.name).isNotNull
        assertThat(restaurant?.ownerName).isNotNull
        assertThat(restaurant?.headChefName).isNotNull
        assertThat(restaurant?.cuisineType).isNotNull
        assertThat(restaurant?.shortDescription).isNotNull
        assertThat(restaurant?.fullDescription).isNotNull
        assertThat(restaurant?.websiteUrl).isNotNull
        assertThat(restaurant?.rating).isGreaterThanOrEqualTo(0)
        assertThat(restaurant?.michelinStarRating).isGreaterThanOrEqualTo(0)
        assertThat(restaurant?.zagatRating).isGreaterThanOrEqualTo(0)
    }
}