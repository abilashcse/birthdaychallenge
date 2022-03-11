package com.abilashcse.birthdayapp.api.remotedatasource

import com.abilashcse.birthdayapp.api.APICallback
import com.abilashcse.birthdayapp.api.RandomUserResponse
import com.abilashcse.birthdayapp.api.services.RandomUserAPIServices
import com.abilashcse.birthdayapp.data.datasource.RandomUserDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RandomUserRemoteDataSource @Inject constructor(private val service: RandomUserAPIServices) :
    RandomUserDataSource {
    private var call: Call<RandomUserResponse>? = null

    override fun getRandomUsers(competitionId: Int, callback: APICallback<RandomUserResponse>) {
        call = service.dateOfBirth(1000)
        call?.enqueue(object : Callback<RandomUserResponse> {
            override fun onResponse(
                call: Call<RandomUserResponse>,
                response: Response<RandomUserResponse>
            ) {
                response.body()?.let {
                    if (response.isSuccessful) {
                        callback.onSuccess(it)
                    } else {
                        callback.onError("Invalid Response")
                    }
                }
            }

            override fun onFailure(call: Call<RandomUserResponse>, t: Throwable) {
                t.message?.let { callback.onError(it) }
            }
        })

    }


}
