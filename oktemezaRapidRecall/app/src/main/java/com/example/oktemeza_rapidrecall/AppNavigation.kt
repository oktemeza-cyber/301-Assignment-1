package com.example.oktemeza_rapidrecall

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.oktemeza_rapidrecall.ui.theme.ui.theme.attemptScreen
import com.example.oktemeza_rapidrecall.ui.theme.ui.theme.gameScreen
import com.example.oktemeza_rapidrecall.ui.theme.ui.theme.logScreen
import com.example.oktemeza_rapidrecall.ui.theme.ui.theme.mainScreen
import com.example.oktemeza_rapidrecall.Attempt
import com.example.oktemeza_rapidrecall.Log


@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    val logs = remember { mutableStateListOf<Log>() }
    val attempts = remember {mutableStateListOf<Attempt>()}

    NavHost(
        navController = navController,
        startDestination = "mainScreen"
    ){

        composable(route = "mainScreen"){
            mainScreen(navController = navController)
        }

        composable(route = "gameScreen"){
            gameScreen(
                attempts = attempts,
                onAddAttempt = {attempts.add(it)},
                onAddLog = {logs.add(it)},
                navController = navController
            )
        }

        composable(route = "attemptScreen"){
            attemptScreen(
                attempts = attempts,
                onAddAttempt = {attempts.add(it)},
                modifier = Modifier,
                navController = navController
            )
        }

        composable(route = "logScreen"){
            logScreen(
                logs = logs,
                onAddLog = {logs.add(it)},
                modifier = Modifier,
                navController = navController)
        }


    }
}
//Main navigation handler