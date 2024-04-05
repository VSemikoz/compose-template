package com.example.compose_template.data.models.person

data class PersonLocationData(
    val street: PersonStreetData,
    val city: String,
    val state: String,
    val country: String,
    val postcode: String,
    val coordinates: PersonCoordinatesData,
    val timezone: PersonTimezoneData,
)