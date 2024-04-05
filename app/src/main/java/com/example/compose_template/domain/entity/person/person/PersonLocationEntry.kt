package com.example.compose_template.domain.entity.person.person

data class PersonLocationEntry(
    val street: PersonStreetEntry,
    val city: String,
    val state: String,
    val country: String,
    val postcode: String,
    val coordinates: PersonCoordinatesEntry,
    val timezone: PersonTimezoneEntry,
)