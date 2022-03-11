package com.abilashcse.birthdayapp.api

interface APICallback<T> {
    fun onSuccess(data: T)
    fun onError(error: String)
}
