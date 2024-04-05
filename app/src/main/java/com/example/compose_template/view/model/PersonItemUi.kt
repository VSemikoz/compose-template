package com.example.compose_template.view.model

import java.time.LocalDate


enum class PersonGender {
    male, female
}

data class PersonItemUi(
    val fullName: String,
    val birthDate: LocalDate,
    val imageUrl: String,
    val gender: PersonGender,
    val location: String,
)