package com.example.compose_template.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.compose_template.data.network.PersonApi
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideOkHttpClientBuilder(
        @ApplicationContext context: Context,
    ): OkHttpClient.Builder = OkHttpClient.Builder().apply {
        addInterceptor(ChuckerInterceptor.Builder(context).build())
        connectTimeout(15, TimeUnit.SECONDS)
        readTimeout(20, TimeUnit.SECONDS)
        writeTimeout(15, TimeUnit.SECONDS)
    }

    @Singleton
    @Provides
    fun providePersonApi(
        moshi: Moshi, builder: OkHttpClient.Builder
    ): PersonApi = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(builder.build()).build().create(PersonApi::class.java)

    private const val BASE_URL = "https://randomuser.me/"
}
