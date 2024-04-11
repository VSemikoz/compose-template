package com.example.compose_template.di

import android.content.Context
import com.example.compose_template.data.sharedPref.PreferencesManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SharedPreferencesModule {
    @Singleton
    @Provides
    fun provideTemplateDatabase(
        @ApplicationContext context: Context,
    ) = PreferencesManager(context)
}