package com.abilashcse.birthdayapp.data.model

import java.text.SimpleDateFormat
import java.util.*

data class Name(val title: String, val first: String, val last: String?)
data class DOB(val date: Date, val age: Int)
data class User(val name: Name, val dob: DOB)

fun DOB.formattedDate(): String {
    val formatter = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    return formatter.format(date)
}

var Name.fullName : String
    get() = "$first $last"
    private set(value) {}
