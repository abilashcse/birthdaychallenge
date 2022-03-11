package com.abilashcse.birthdayapp.data.model

import java.util.*

//"name":{"title":"Miss","first":"Pamela","last":"Howell"},"dob":{"date":"1980-02-26T07:16:39.510Z","age":42}}
data class Name(val title: String, val first: String, val last: String)
data class DOB(val date: Date, val age: Int)
data class User(val name: Name, val dob: DOB)
