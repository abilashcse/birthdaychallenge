package com.abilashcse.birthdayapp.ui.common

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.abilashcse.birthdayapp.data.model.Name
import com.abilashcse.birthdayapp.ui.theme.BirthdayAppTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CommonUITests {

    @get:Rule
    val composeTestRule = createComposeRule()
    @Test
    fun testNameChipUI() {
        val testName = Name(title = "Mr", first = "User", "Name")
        composeTestRule.setContent {
            BirthdayAppTheme {
                nameChip(name = testName)
            }
        }
        with(composeTestRule) {
            val initialText = composeTestRule.onNode(hasTestTag("initialTag"), useUnmergedTree = true)
            initialText.assertIsDisplayed()
            onNodeWithText("UN").assertIsDisplayed()
        }
    }

}
