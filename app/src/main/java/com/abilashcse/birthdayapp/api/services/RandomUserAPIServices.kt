package com.abilashcse.birthdayapp.api.services

import com.abilashcse.birthdayapp.api.RandomUserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUserAPIServices {
    @GET(".")
    fun dateOfBirth(
        @Query("results") results: Int = 100,
        @Query("seed") seed: String = "chalkboard",
        @Query("inc") inc: String = "name,dob"
    ): Call<RandomUserResponse>
}
