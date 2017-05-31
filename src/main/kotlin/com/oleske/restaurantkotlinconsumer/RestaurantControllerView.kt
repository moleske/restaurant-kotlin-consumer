package com.oleske.restaurantkotlinconsumer

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.ModelAndView

@Controller
class RestaurantControllerView (val restaurantGetter: RestaurantGetter) {
    @GetMapping("/")
    fun restaurantList(): ModelAndView {
        val modelAndView = ModelAndView("list")
        modelAndView.modelMap.addAttribute("restaurants", restaurantGetter.getRestaurants())

        return modelAndView
    }
}
