package com.example.compose_template.view.adapter

import com.example.compose_template.domain.entity.person.PersonEntry
import com.example.compose_template.view.model.PersonGender
import com.example.compose_template.view.model.PersonItemUi
import java.time.LocalDate
import java.time.format.DateTimeFormatter


fun PersonEntry.toUi(): PersonItemUi {
    val parsedDate = LocalDate.parse(dob.date, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
    return PersonItemUi(
        fullName = "${name.title} ${name.first} ${name.last}",
        birthDate = parsedDate,
        imageUrl = picture.large,
        gender = gender.getGender(),
        location = "${location.country},${location.state},${location.city},${location.street.name} ${location.street.number}",
    )
}


private fun String.getGender(): PersonGender {
    return if (this == "female") {
        PersonGender.female
    } else {
        PersonGender.male
    }
}
