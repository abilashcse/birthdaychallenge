package com.abilashcse.birthdayapp.data.model

import com.abilashcse.birthdayapp.api.APICallback
import com.abilashcse.birthdayapp.api.RandomUserResponse

class RandomUsersRepository (private val standingDataSource: RandomUserDataSource){
    fun getStandings(competitionId: Int, callback:APICallback<RandomUserResponse>) {
        standingDataSource.getRandomUsers(competitionId, callback)
    }
}
