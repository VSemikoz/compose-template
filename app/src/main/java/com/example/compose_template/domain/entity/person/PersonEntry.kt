package com.example.compose_template.domain.entity.person

data class PersonEntry(
    val gender: String,
    val name: PersonNameEntry,
    val location: PersonLocationEntry,
    val email: String,
    val registered: PersonRegisteredEntry,
    val dob: PersonDobEntry,
    val phone: String,
    val cell: String,
    val id: PersonIdEntry,
    val picture: PersonPictureEntry,
)
