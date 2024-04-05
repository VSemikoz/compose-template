package com.example.compose_template.data.models.person

data class PersonData(
    val gender: String,
    val name: PersonNameData,
    val location: PersonLocationData,
    val email: String,
    val registered: PersonRegisteredData,
    val dob: PersonDobData,
    val phone: String,
    val cell: String,
    val id: PersonIdData,
    val picture: PersonPictureData,
    val nat: String,
)
