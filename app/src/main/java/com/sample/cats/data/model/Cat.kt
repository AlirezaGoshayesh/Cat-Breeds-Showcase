package com.sample.cats.data.model

data class Cat(
    val description: String,
    val id: String,
    val image: Image?,
    val life_span: String,
    val name: String,
    val origin: String,
    val temperament: String
)