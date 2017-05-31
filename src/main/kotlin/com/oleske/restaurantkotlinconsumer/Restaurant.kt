package com.oleske.restaurantkotlinconsumer

data class Restaurant(val id: Long = 0,
                      val name: String = "",
                      val ownerName: String = "",
                      val headChefName: String = "",
                      val cuisineType: String = "",
                      val shortDescription: String = "",
                      val fullDescription: String = "",
                      val websiteUrl: String = "",
                      val rating: Int = 0,
                      val michelinStarRating: Int = 0,
                      val zagatRating: Int = 0
)