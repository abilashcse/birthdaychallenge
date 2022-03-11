package com.abilashcse.birthdayapp.di

import com.abilashcse.birthdayapp.api.services.RandomUserAPIServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

//https://randomuser.me/api/?results=1000&seed=chalkboard&inc=name,dob
@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {
    private const val API_BASE_URL = "https://randomuser.me/api/"

    @Provides
    @Singleton
    fun provideRandomUserAPIServices(retrofit: Retrofit): RandomUserAPIServices {

        val apiService = retrofit.create(
            RandomUserAPIServices::class.java
        )
        return apiService as RandomUserAPIServices
    }

    @Provides
    @Singleton
    fun provideRetrofit(httpLoggingInterceptor: HttpLoggingInterceptor): Retrofit {
        val builder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

        val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        httpClient.addInterceptor(httpLoggingInterceptor)

        return builder.client(httpClient.build()).build()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }
}
