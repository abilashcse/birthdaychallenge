package com.abilashcse.birthdayapp.data.model

import org.junit.Assert
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

class ModelTest {

    @Test
    fun testFormattedDate() {
        val march10Str = "10-03-1990"
        val formatter = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        val dateMarch10 = formatter.parse(march10Str)
        val dateToTest = DOB(date = dateMarch10, age = 32)
        Assert.assertEquals(march10Str, dateToTest.formattedDate())
    }
    
    @Test
    fun testFullName() {
        val name = Name("Mr", "User", "One")
        Assert.assertEquals("User One", name.fullName)
    }
}
