package com.abilashcse.birthdayapp.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface RandomUserAPIServices {
    @GET(".")
    fun dateOfBirth(@Query("results")results: Int,
                    @Query("seed") seed: String = "chalkboard",
    ): Call<RandomUserResponse>
}
