package com.example.compose_template.data.database.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import java.util.Date
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@ProvidedTypeConverter
class DateConverters @Inject constructor() {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}