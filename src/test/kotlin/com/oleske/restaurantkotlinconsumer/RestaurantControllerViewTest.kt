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
    val resturantGetter: RestaurantGetter? = null

    @Autowired
    val webDriver: WebDriver? = null

    var restaurantOneName: String = ""
    var restaurantTwoName: String = ""

    @Before
    fun setUp() {
        restaurantOneName = Math.random().toString()
        restaurantTwoName = Math.random().toString()
        `when`(resturantGetter!!.getRestaurants()).thenReturn(
                listOf(Restaurant(name = restaurantOneName), Restaurant(name = restaurantTwoName)))

        webDriver!!.get("/")
    }

    @Test
    fun getHome_hasHeader() {
        val header = webDriver!!.findElement(By.tagName("h1"))

        assertThat(header.text).isEqualTo("Restaurant List")
    }

    @Test
    fun getHome_DisplaysRestaurants() {
        val restaurants = webDriver!!.findElements(By.tagName("div"))

        assertThat(restaurants.size).isEqualTo(2)
        assertThat(restaurants[0].text).isEqualTo(restaurantOneName)
        assertThat(restaurants[1].text).isEqualTo(restaurantTwoName)
    }
}