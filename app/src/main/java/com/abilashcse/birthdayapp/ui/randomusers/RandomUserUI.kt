package com.abilashcse.birthdayapp.ui.randomusers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.navigation.NavHostController
import com.abilashcse.birthdayapp.R
import com.abilashcse.birthdayapp.data.model.DOB
import com.abilashcse.birthdayapp.data.model.Name
import com.abilashcse.birthdayapp.data.model.User
import com.abilashcse.birthdayapp.data.model.fullName
import com.abilashcse.birthdayapp.ui.common.nameChip
import com.google.gson.Gson
import java.util.*

@Composable
fun RandomUserUI(
    userJson: String,
    navController: NavHostController?
) {
    val user = Gson().fromJson(userJson, User::class.java)
    UserDetailsUI(user = user, navController = navController)
}

@Composable
fun UserDetailsUI(user: User, navController: NavHostController?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(modifier = Modifier.padding(4.dp)) {
            nameChip(user.name, textSize = 40f)
        }
        Text(
            text = user.name.fullName(),
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = "${user.dob.age} ${stringResource(id = R.string.years_old)}",
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            modifier = Modifier.padding(2.dp)
        )
        Button(
            modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 20.dp, end = 20.dp),
            onClick = {
                navController?.popBackStack()
            }
        ) {
            Text(stringResource(R.string.go_back))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RandomUserPreview() {
    val date = Date()
    val user1 = User(
        name = Name(title = "Mr", first = "User", last = "One"),
        dob = DOB(date = date, age = 23)
    )
    UserDetailsUI(user = user1, navController = null)
}
