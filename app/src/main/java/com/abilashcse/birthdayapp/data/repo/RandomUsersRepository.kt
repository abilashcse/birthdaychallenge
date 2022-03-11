package com.abilashcse.birthdayapp.data.repo

import com.abilashcse.birthdayapp.api.APICallback
import com.abilashcse.birthdayapp.api.RandomUserResponse
import com.abilashcse.birthdayapp.data.datasource.RandomUserDataSource

class RandomUsersRepository (private val standingDataSource: RandomUserDataSource){
    fun getStandings(competitionId: Int, callback:APICallback<RandomUserResponse>) {
        standingDataSource.getRandomUsers(competitionId, callback)
    }
}
