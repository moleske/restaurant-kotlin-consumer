package com.oleske.restaurantkotlinconsumer

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.junit4.SpringRunner


@RunWith(SpringRunner::class)
@WebMvcTest(RestaurantControllerView::class)
class RestaurantControllerViewTest {
    @Autowired
    val webDriver: WebDriver? = null

    @Test
    fun getHome_hasHeader() {
        webDriver!!.get("/")
        val header = webDriver.findElement(By.tagName("h1"))
        assertThat(header.text).isEqualTo("Restaurant List")
    }
}