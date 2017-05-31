package com.oleske.restaurantkotlinconsumer

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.ModelAndView

@Controller
class RestaurantControllerView {
    @GetMapping("/")
    fun restaurantList(): ModelAndView = ModelAndView("list")
}
