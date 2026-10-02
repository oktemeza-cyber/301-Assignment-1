package com.example.oktemeza_rapidrecall.ui.theme.ui.theme

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.oktemeza_rapidrecall.Attempt
import com.example.oktemeza_rapidrecall.Log



@Composable
fun startButton(modifier: Modifier = Modifier, navController: NavHostController){
    val context = LocalContext.current
    Button(
        onClick = {
            navController.navigate("gameScreen")
        },
        modifier = modifier
    ) {
        Text(text = "Start")
    }

}

@Composable
fun logButton(modifier: Modifier = Modifier, navController: NavHostController){
    val context = LocalContext.current
    Button(
        onClick = {
            navController.navigate("logScreen")

        }, modifier = modifier

    ) {
        Text(text = "Log")
    }

}

@Composable
fun attemptButton(modifier: Modifier = Modifier, navController: NavHostController){
    val context = LocalContext.current
    Button(
        onClick = {
            navController.navigate("attemptScreen")
        }, modifier = modifier

    ) {
        Text(text = "Attempts")
    }

}


@Composable
fun mainScreen(navController: NavHostController) {
    OktemezaRapidRecallTheme() {
        startButton(Modifier.offset(93.dp, 400.dp).fillMaxWidth(0.5f), navController)
        logButton(Modifier.offset(93.dp, 460.dp).fillMaxWidth(0.5f), navController)
        attemptButton(Modifier.offset(93.dp, 520.dp).fillMaxWidth(0.5f), navController)
        Text(
            text = ("Welcome to Rapid Recall"),
            fontSize = 33.sp,
            lineHeight = 40.sp,
            modifier = Modifier.padding(100.dp, 300.dp).fillMaxWidth(0.9f)
        )
    }
}

