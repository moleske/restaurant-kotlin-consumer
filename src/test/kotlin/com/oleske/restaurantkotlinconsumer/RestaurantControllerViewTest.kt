package com.oleske.restaurantkotlinconsumer

import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit4.SpringRunner


@RunWith(SpringRunner::class)
@WebMvcTest(RestaurantControllerView::class)
class RestaurantControllerViewTest {
    @MockBean
    val restaurantGetter: RestaurantGetter? = null

    @Autowired
    val webDriver: WebDriver? = null

    lateinit var restaurantOneExpected: Restaurant
    lateinit var restaurantTwoExpected: Restaurant

    @Before
    fun setUp() {
        restaurantOneExpected = Restaurant(
                name = Math.random().toString(),
                cuisineType = Math.random().toString(),
                websiteUrl = Math.random().toString(),
                rating = Math.random().toInt()
        )
        restaurantTwoExpected = Restaurant(
                name = Math.random().toString(),
                cuisineType = Math.random().toString(),
                websiteUrl = Math.random().toString(),
                rating = Math.random().toInt()
        )

        `when`(restaurantGetter!!.getRestaurants()).thenReturn(
                listOf(restaurantOneExpected, restaurantTwoExpected))

        webDriver!!.get("/")
    }

    @Test
    fun getHome_hasHeader() {
        val header = webDriver!!.findElement(By.tagName("h1"))

        assertThat(header.text).isEqualTo("Restaurant List")
    }

    @Test
    fun getHome_DisplaysRestaurants() {
        val restaurants = webDriver!!.findElements(By.tagName("tr"))

        assertThat(restaurants.size).isEqualTo(3)
        val restaurantOne = restaurants[1].findElements(By.tagName("td"))
        val restaurantTwo = restaurants[2].findElements(By.tagName("td"))

        assertThat(restaurantOne[0].text).isEqualTo(restaurantOneExpected.name)
        assertThat(restaurantOne[1].text).isEqualTo(restaurantOneExpected.cuisineType)
        assertThat(restaurantOne[2].text).isEqualTo(restaurantOneExpected.websiteUrl)
        assertThat(restaurantOne[3].text).isEqualTo(restaurantOneExpected.rating.toString())

        assertThat(restaurantTwo[0].text).isEqualTo(restaurantTwoExpected.name)
        assertThat(restaurantTwo[1].text).isEqualTo(restaurantTwoExpected.cuisineType)
        assertThat(restaurantTwo[2].text).isEqualTo(restaurantTwoExpected.websiteUrl)
        assertThat(restaurantTwo[3].text).isEqualTo(restaurantTwoExpected.rating.toString())
    }
}