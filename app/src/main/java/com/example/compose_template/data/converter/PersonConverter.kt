package com.example.compose_template.data.converter

import PersonEntry
import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.compose_template.data.models.person.PersonCoordinatesData
import com.example.compose_template.data.models.person.PersonData
import com.example.compose_template.data.models.person.PersonDobData
import com.example.compose_template.data.models.person.PersonIdData
import com.example.compose_template.data.models.person.PersonLocationData
import com.example.compose_template.data.models.person.PersonNameData
import com.example.compose_template.data.models.person.PersonPictureData
import com.example.compose_template.data.models.person.PersonRegisteredData
import com.example.compose_template.data.models.person.PersonResultData
import com.example.compose_template.data.models.person.PersonResultInfoData
import com.example.compose_template.data.models.person.PersonStreetData
import com.example.compose_template.data.models.person.PersonTimezoneData
import com.example.compose_template.domain.entity.person.person.PersonCoordinatesEntry
import com.example.compose_template.domain.entity.person.person.PersonDobEntry
import com.example.compose_template.domain.entity.person.person.PersonIdEntry
import com.example.compose_template.domain.entity.person.person.PersonLocationEntry
import com.example.compose_template.domain.entity.person.person.PersonNameEntry
import com.example.compose_template.domain.entity.person.person.PersonPictureEntry
import com.example.compose_template.domain.entity.person.person.PersonRegisteredEntry
import com.example.compose_template.domain.entity.person.person.PersonResultEntry
import com.example.compose_template.domain.entity.person.person.PersonResultInfoEntry
import com.example.compose_template.domain.entity.person.person.PersonStreetEntry
import com.example.compose_template.domain.entity.person.person.PersonTimezoneEntry
import com.squareup.moshi.Moshi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@ProvidedTypeConverter
class PersonConverter @Inject constructor(moshi: Moshi) {
    private val personCoordinatesConverter = moshi.adapter(PersonCoordinatesData::class.java)
    private val personConverter = moshi.adapter(PersonData::class.java)
    private val personIdConverter = moshi.adapter(PersonIdData::class.java)
    private val personLocationConverter = moshi.adapter(PersonLocationData::class.java)
    private val personNameConverter = moshi.adapter(PersonNameData::class.java)
    private val personPictureConverter = moshi.adapter(PersonPictureData::class.java)
    private val personRegisteredConverter = moshi.adapter(PersonRegisteredData::class.java)
    private val personResultConverter = moshi.adapter(PersonResultData::class.java)
    private val personResultInfoConverter = moshi.adapter(PersonResultInfoData::class.java)
    private val personStreetConverter = moshi.adapter(PersonStreetData::class.java)
    private val personTimezoneConverter = moshi.adapter(PersonTimezoneData::class.java)
    private val personDobConverter = moshi.adapter(PersonDobData::class.java)

    @TypeConverter
    fun fromEntityToJson(data: PersonCoordinatesData): String = personCoordinatesConverter.toJson(data)

    @TypeConverter
    fun toPersonCoordinates(data: String): PersonCoordinatesData =
        checkNotNull(personCoordinatesConverter.fromJson(data))

    @TypeConverter
    fun intoDomain(data: PersonCoordinatesData): PersonCoordinatesEntry {
        return data.intoDomain()
    }


    @TypeConverter
    fun fromEntityToJson(data: PersonData): String = personConverter.toJson(data)

    @TypeConverter
    fun toPerson(data: String): PersonData =
        checkNotNull(personConverter.fromJson(data))

    @TypeConverter
    fun intoDomain(data: PersonData): PersonEntry {
        return data.intoDomain()
    }

    @TypeConverter
    fun fromEntityToJson(data: PersonIdData): String = personIdConverter.toJson(data)

    @TypeConverter
    fun toPersonId(data: String): PersonIdData =
        checkNotNull(personIdConverter.fromJson(data))

    @TypeConverter
    fun intoDomain(data: PersonIdData): PersonIdEntry {
        return data.intoDomain()
    }

    @TypeConverter
    fun fromEntityToJson(data: PersonLocationData): String = personLocationConverter.toJson(data)

    @TypeConverter
    fun toPersonLocation(data: String): PersonLocationData =
        checkNotNull(personLocationConverter.fromJson(data))

    @TypeConverter
    fun intoDomain(data: PersonLocationData): PersonLocationEntry {
        return data.intoDomain()
    }

    @TypeConverter
    fun fromEntityToJson(data: PersonNameData): String = personNameConverter.toJson(data)

    @TypeConverter
    fun toPersonName(data: String): PersonNameData =
        checkNotNull(personNameConverter.fromJson(data))

    @TypeConverter
    fun intoDomain(data: PersonNameData): PersonNameEntry {
        return data.intoDomain()
    }

    @TypeConverter
    fun fromEntityToJson(data: PersonPictureData): String = personPictureConverter.toJson(data)

    @TypeConverter
    fun toPersonPicture(data: String): PersonPictureData =
        checkNotNull(personPictureConverter.fromJson(data))

    @TypeConverter
    fun intoDomain(data: PersonPictureData): PersonPictureEntry {
        return data.intoDomain()
    }

    @TypeConverter
    fun fromEntityToJson(data: PersonRegisteredData): String = personRegisteredConverter.toJson(data)

    @TypeConverter
    fun toPersonRegistered(data: String): PersonRegisteredData =
        checkNotNull(personRegisteredConverter.fromJson(data))

    @TypeConverter
    fun intoDomain(data: PersonRegisteredData): PersonRegisteredEntry {
        return data.intoDomain()
    }

    @TypeConverter
    fun fromEntityToJson(data: PersonResultData): String = personResultConverter.toJson(data)

    @TypeConverter
    fun toEntityFromJson(data: String): PersonResultData =
        checkNotNull(personResultConverter.fromJson(data))

    @TypeConverter
    fun intoDomain(data: PersonResultData): PersonResultEntry {
        return data.intoDomain()
    }

    @TypeConverter
    fun fromEntityToJson(data: PersonResultInfoData): String = personResultInfoConverter.toJson(data)

    @TypeConverter
    fun toPersonInfo(data: String): PersonResultInfoData =
        checkNotNull(personResultInfoConverter.fromJson(data))

    @TypeConverter
    fun intoDomain(data: PersonResultInfoData): PersonResultInfoEntry {
        return data.intoDomain()
    }

    @TypeConverter
    fun fromEntityToJson(data: PersonStreetData): String = personStreetConverter.toJson(data)

    @TypeConverter
    fun toPersonStreet(data: String): PersonStreetData =
        checkNotNull(personStreetConverter.fromJson(data))

    @TypeConverter
    fun intoDomain(data: PersonStreetData): PersonStreetEntry {
        return data.intoDomain()
    }

    @TypeConverter
    fun fromEntityToJson(data: PersonTimezoneData): String = personTimezoneConverter.toJson(data)

    @TypeConverter
    fun toPersonTimezone(data: String): PersonTimezoneData =
        checkNotNull(personTimezoneConverter.fromJson(data))

    @TypeConverter
    fun intoDomain(data: PersonTimezoneData): PersonTimezoneEntry {
        return data.intoDomain()
    }


    @TypeConverter
    fun fromEntityToJson(data: PersonDobData): String = personDobConverter.toJson(data)

    @TypeConverter
    fun toPersonDob(data: String): PersonDobData =
        checkNotNull(personDobConverter.fromJson(data))

    @TypeConverter
    fun intoDomain(data: PersonDobData): PersonDobEntry {
        return data.intoDomain()
    }

}


private fun PersonCoordinatesData.intoDomain(): PersonCoordinatesEntry {
    return PersonCoordinatesEntry(
        latitude = latitude,
        longitude = longitude,
    )
}

private fun PersonData.intoDomain(): PersonEntry {
    return PersonEntry(
        gender = gender,
        name = name.intoDomain(),
        location = location.intoDomain(),
        email = email,
        registered = registered.intoDomain(),
        phone = phone,
        cell = cell,
        id = id.intoDomain(),
        picture = picture.intoDomain(),
        dob = dob.intoDomain(),
    )
}

private fun PersonDobData.intoDomain(): PersonDobEntry {
    return PersonDobEntry(
        date = date,
        age = age,
    )
}

private fun PersonIdData.intoDomain(): PersonIdEntry {
    return PersonIdEntry(
        name = name,
        value = value,
    )
}

private fun PersonLocationData.intoDomain(): PersonLocationEntry {
    return PersonLocationEntry(
        street = street.intoDomain(),
        city = city,
        state = state,
        country = country,
        postcode = postcode,
        coordinates = coordinates.intoDomain(),
        timezone = timezone.intoDomain(),
    )
}


private fun PersonNameData.intoDomain(): PersonNameEntry {
    return PersonNameEntry(
        title = title,
        first = first,
        last = last,
    )
}

private fun PersonPictureData.intoDomain(): PersonPictureEntry {
    return PersonPictureEntry(
        large = large,
        medium = medium,
        thumbnail = thumbnail,
    )
}

private fun PersonRegisteredData.intoDomain(): PersonRegisteredEntry {
    return PersonRegisteredEntry(
        date = date,
        age = age,
    )
}

private fun PersonResultData.intoDomain(): PersonResultEntry {
    return PersonResultEntry(
        results = results.map { it.intoDomain() },
        info = info.intoDomain(),
    )
}

private fun PersonResultInfoData.intoDomain(): PersonResultInfoEntry {
    return PersonResultInfoEntry(
        results = results,
        page = page,
    )
}

private fun PersonStreetData.intoDomain(): PersonStreetEntry {
    return PersonStreetEntry(
        number = number,
        name = name,
    )
}

private fun PersonTimezoneData.intoDomain(): PersonTimezoneEntry {
    return PersonTimezoneEntry(
        offset = offset,
        description = description,
    )
}
