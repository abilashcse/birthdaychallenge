package com.abilashcse.birthdayapp.ui.randomusers

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.abilashcse.birthdayapp.viewmodels.randomuser.RandomUserViewModel

@Composable
fun RandomUserUI(
    name: String,
    viewModel: RandomUserViewModel = hiltViewModel()
) {
    Text(text = "Hello $name!")
    viewModel.getRandomUsers(1000)
}


@Preview(showBackground = true)
@Composable
fun RandomUserPreview() {
    RandomUserUI(name = "Team")
}
