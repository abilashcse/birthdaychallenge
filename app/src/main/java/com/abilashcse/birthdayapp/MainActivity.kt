package com.abilashcse.birthdayapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.abilashcse.birthdayapp.data.model.*
import com.abilashcse.birthdayapp.ui.common.LoadingUI
import com.abilashcse.birthdayapp.ui.common.nameChip
import com.abilashcse.birthdayapp.ui.randomusers.RandomUserUI
import com.abilashcse.birthdayapp.ui.theme.BirthdayAppTheme
import com.abilashcse.birthdayapp.viewmodels.randomuser.RandomUserViewModel
import com.google.gson.Gson
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
    var navController = rememberNavController()
    NavHost(navController, startDestination = "user") {
        composable(route = "user") {
            UserList(navController = navController)
        }
        composable(route = "userdata/{user}",
         arguments = listOf(navArgument("user") {
             type = NavType.StringType})) {
                backStackEntry ->
            RandomUserUI(userJson = backStackEntry.arguments?.getString("user")!!,
            navController = navController)
        }
    }
}

@Composable
fun UserList(viewModel: RandomUserViewModel = hiltViewModel(),
             navController: NavHostController?) {
    val userList by viewModel.randomUsers.observeAsState(initial = emptyList())
    if (userList.isEmpty()) {
        LoadingUI()
    } else {
        UserListUI(userList, navController = navController)
    }
    viewModel.getRandomUsers(1000)
}

@Composable
private fun UserListUI(userList: List<User>, navController: NavHostController?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.header_title),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )
        LazyColumn() {
            items(userList) { user ->
                UserRow(user = user, navController = navController)
                Divider(color = Color.Black, thickness = 1.dp)
            }
        }
    }
}

@Composable
fun UserRow(user: User, navController: NavHostController?) {
    LazyRow(modifier = Modifier.padding(8.dp)
        .fillMaxSize()
        .clickable {
            navController?.navigate("userdata/${Gson().toJson(user)}")
        }
    ) {
        item {
            Column(modifier = Modifier.padding(4.dp)) {
                nameChip(user.name)
            }
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = user.name.fullName,
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
    val users: MutableList<User> = ArrayList()
    val date = Date()
    val user1 = User(
        name = Name(title = "Mr", first = "User", last = "One"),
        dob = DOB(date = date, age = 23)
    )
    val user2 = User(
        name = Name(title = "Mr", first = "User", last = "Two"),
        dob = DOB(date = date, age = 23)
    )
    users.add(user1)
    users.add(user2)
    UserListUI(userList = users, navController = null)
}

