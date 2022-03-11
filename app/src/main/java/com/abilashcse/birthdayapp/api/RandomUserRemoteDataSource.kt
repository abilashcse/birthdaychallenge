package com.abilashcse.birthdayapp.api

import com.abilashcse.birthdayapp.data.model.RandomUserDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RandomUserRemoteDataSource @Inject constructor(private val service: RandomUserAPIServices) : RandomUserDataSource{
    private var call: Call<RandomUserResponse>?= null

    override fun getRandomUsers(competitionId: Int, callback: APICallback<RandomUserResponse>) {
        call = service.dateOfBirth(1000)
        call?.enqueue(object :Callback<RandomUserResponse> {
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
