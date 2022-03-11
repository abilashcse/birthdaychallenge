package com.abilashcse.birthdayapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.abilashcse.birthdayapp.ui.randomusers.RandomUserUI
import com.abilashcse.birthdayapp.ui.theme.ComposetestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposetestTheme {
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
            Greeting("World!", navController)
        }
        composable(route = "userdata") {
            RandomUserUI("TeamName")
        }
    }
}

@Composable
fun Greeting(name: String, navController: NavHostController?) {

    Column(Modifier.padding(Dp(5F))) {
        Row() {
            Text(text = "Hello $name!")
        }
        Row() {
            Button(onClick = {
                navController?.navigate("userdata")
            }) {
                Text(text = "Next screen")
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun ComposablePreview() {
    Greeting("World", null)
}
