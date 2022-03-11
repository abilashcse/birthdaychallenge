package com.abilashcse.birthdayapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.abilashcse.birthdayapp.data.model.DOB
import com.abilashcse.birthdayapp.data.model.Name
import com.abilashcse.birthdayapp.data.model.User
import com.abilashcse.birthdayapp.data.model.formattedDate
import com.abilashcse.birthdayapp.ui.common.LoadingUI
import com.abilashcse.birthdayapp.ui.common.nameChip
import com.abilashcse.birthdayapp.ui.randomusers.RandomUserUI
import com.abilashcse.birthdayapp.ui.theme.BirthdayAppTheme
import com.abilashcse.birthdayapp.viewmodels.randomuser.RandomUserViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BirthdayAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainApp()
                }
            }
        }
    }
}

@Composable
fun MainApp() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "user") {
        composable(route = "user") {
            UserList()
        }
        composable(route = "userdata") {
            RandomUserUI("TeamName")
        }
    }
}

@Composable
fun UserList(viewModel: RandomUserViewModel = hiltViewModel()) {
    val userList by viewModel.randomUsers.observeAsState(initial = emptyList())
    if (userList.isEmpty()) {
        LoadingUI()
    } else {
        UserListUI(userList)
    }
    viewModel.getRandomUsers(1000)
}

@Composable
private fun UserListUI(userList: List<User>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = Dp(16f)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.header_title),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(Dp(16f))
        )

        LazyColumn() {
            items(userList) { user ->
                UserRow(user = user)
                Divider(color = Color.Black, thickness = Dp(1f))
            }
        }
    }
}

@Composable
fun UserRow(user: User) {
    LazyRow(modifier = Modifier.padding(Dp(8f))) {
        item {
            Column(modifier = Modifier.padding(Dp(4f))) {
                nameChip(user.name)
            }
            Column(modifier = Modifier.padding(Dp(8f))) {
                Text(
                    text = "${user.name.first} ${user.name.last}",
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = user.dob.formattedDate(),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ComposablePreview() {
    var users: MutableList<User> = ArrayList()
    val date = Date()
    var user1 = User(
        name = Name(title = "Mr", first = "User", last = "One"),
        dob = DOB(date = date, age = 23)
    )
    var user2 = User(
        name = Name(title = "Mr", first = "User", last = "Two"),
        dob = DOB(date = date, age = 23)
    )
    users.add(user1)
    users.add(user2)
    UserListUI(userList = users)
}

