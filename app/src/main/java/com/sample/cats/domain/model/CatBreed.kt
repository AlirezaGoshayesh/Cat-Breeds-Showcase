package com.sample.cats.domain.model

data class CatBreed(
    val description: String,
    val id: String,
    val image: String?,
    val lifeSpan: String,
    val name: String,
    val origin: String,
    val temperament: String,
    val isFavorite: Boolean = false
)