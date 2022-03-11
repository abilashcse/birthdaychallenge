package com.abilashcse.birthdayapp.viewmodels.randomuser

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abilashcse.birthdayapp.api.APICallback
import com.abilashcse.birthdayapp.api.RandomUserResponse
import com.abilashcse.birthdayapp.data.repo.RandomUsersRepository
import com.abilashcse.birthdayapp.data.model.User
import com.abilashcse.birthdayapp.viewmodels.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RandomUserViewModel @Inject constructor(var repository: RandomUsersRepository): BaseViewModel() {
    private val _randomUsers= MutableLiveData<List<User>>().apply { value = emptyList() }
    val randomUsers: LiveData<List<User>> = _randomUsers


    fun getRandomUsers(count: Int) {
        _isViewLoading.value = true
        repository.getStandings(count, object : APICallback<RandomUserResponse> {

            override fun onSuccess(data: RandomUserResponse) {
                Log.d("Test", "data = ${data.results}")
                _isViewLoading.value = false
                if (data.results.isEmpty()) {
                    _isEmptyList.value = true

                } else {
                    _randomUsers.value = data.results
                }
            }

            override fun onError(error: String) {
                _isViewLoading.value = false
                _onMessageError.value = error
            }
        })
    }
}
