package com.example.compose_template.view.model

import java.time.LocalDate

data class PersonItemUi(
    val fullName: String,
    val birthDate: LocalDate,
    val imageUrl: String,
    val gender: PersonGender,
    val location: String,
)