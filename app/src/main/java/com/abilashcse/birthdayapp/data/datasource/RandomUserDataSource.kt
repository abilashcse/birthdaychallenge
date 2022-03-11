package com.abilashcse.birthdayapp.data.datasource

import com.abilashcse.birthdayapp.api.APICallback
import com.abilashcse.birthdayapp.api.RandomUserResponse

interface RandomUserDataSource {
    fun getRandomUsers(competitionId: Int, callback: APICallback<RandomUserResponse>)
}
