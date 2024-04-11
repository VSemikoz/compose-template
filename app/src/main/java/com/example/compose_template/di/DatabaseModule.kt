package com.example.compose_template.di

import android.content.Context
import androidx.room.Room
import com.example.compose_template.data.database.TemplateDatabase
import com.example.compose_template.data.database.converter.DateConverter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val DATABASE_NAME = "template-database"

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideTemplateDatabase(
        @ApplicationContext context: Context,
        dateConverter: DateConverter,
    ) = Room.databaseBuilder(context, TemplateDatabase::class.java, DATABASE_NAME).addTypeConverter(dateConverter)
        .build()
}