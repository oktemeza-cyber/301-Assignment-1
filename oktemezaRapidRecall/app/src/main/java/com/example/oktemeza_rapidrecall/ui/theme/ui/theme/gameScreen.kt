package com.example.oktemeza_rapidrecall.ui.theme.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.Button
import com.example.oktemeza_rapidrecall.Attempt
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.DisposableEffect
import kotlinx.coroutines.delay
import com.example.oktemeza_rapidrecall.Log


@Composable
fun gameScreen(
    attempts: List<Attempt>,
    onAddAttempt: (Attempt) -> Unit,
    onAddLog: (Log) -> Unit,
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    var numAttempts by remember {mutableStateOf(0)}
    var correctAttempts by remember{mutableStateOf(0)}
    var accuracy by remember {mutableStateOf(0.0f)}
    var userLength by remember { mutableStateOf("") }
    val sequenceToGuess =  remember {mutableStateListOf<String>()}
    var playerGuess by remember { mutableStateOf("") }
    var correct by remember { mutableStateOf(false) }
    //for updates in log and attempt


    var showCount by remember{mutableStateOf(0)}
    var startSequence by remember{mutableStateOf(false)}
    var timeIsUp by remember {mutableStateOf(false)}
    var showResults by remember{mutableStateOf(false)}
    var guessCount by remember{mutableStateOf(0)}

    LaunchedEffect(startSequence) {
        if(startSequence){
            showCount = 0
            for(i in 1..sequenceToGuess.size){
                delay(300)
                showCount = i
                delay(1500)
                showCount = 0
            }
            startSequence = false
            timeIsUp = true
        }
    }


    DisposableEffect(Unit){
        onDispose {
            if(guessCount > 0){
                onAddLog(
                    Log(
                        sequenceToGuess.size.toString(),
                        playerGuess,
                        sequenceToGuess.joinToString(""),
                        correct,
                        (System.currentTimeMillis() / 1000).toInt()
                    )
                )
            }
        }
    }


    var showDialog by remember {mutableStateOf(true)}
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { },
            title = {Text("Enter Sequence Length (1-10)")},
            confirmButton = {
                Button(
                    enabled = userLength.isNotEmpty(),
                    onClick = {
                    val length = userLength.toInt()

                    sequenceToGuess.clear()
                    repeat(userLength.toInt()){
                        sequenceToGuess.add((0..9).random().toString())
                    }

                    showDialog = false
                    startSequence = true
                }) { Text("OK") }
            },
            text = {
                OutlinedTextField(
                    value = userLength,
                    onValueChange = {
                        if (it.isEmpty() || (it.length == 1 && it[0] in '1'..'9')) {
                            userLength = it
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
            },

            )
    }





    Column(modifier = modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Button(
                onClick = {
                    navController.popBackStack()
                }, modifier = Modifier
            ) {
                Text(text = "Back")
            }

            Text(
                text = sequenceToGuess.getOrNull(showCount - 1) ?: "",
                fontSize = 45.sp,
                modifier = Modifier.padding(100.dp, 300.dp).fillMaxWidth(0.9f)
            )
        }
    }


    if(showResults) {
        Text(
            text = if (correct) "Correct!" else "Wrong, Guess again?",
            fontSize = 20.sp,
            modifier = Modifier.padding(100.dp, 350.dp).fillMaxWidth(0.9f)
        )


        Button(
            onClick = {
                playerGuess = ""
                userLength = ""
                showResults = false
                timeIsUp = true
            },
            Modifier.padding(100.dp, 375.dp).fillMaxWidth(0.9f)
        ) {
            Text("Guess again")
        }
    }


    if (timeIsUp) {
        AlertDialog(
            onDismissRequest = { },
            title = {Text("What was the sequence?")},
            confirmButton = {
                Button(onClick = {
                    val target = sequenceToGuess.joinToString("")
                    correct = (playerGuess == target)
                    guessCount++

                    // totals come from the saved list, so they carry across visits
                    numAttempts = attempts.size + 1
                    correctAttempts = (attempts.lastOrNull()?.correctAttempt ?: 0) + if (correct) 1 else 0
                    accuracy = correctAttempts.toFloat() / numAttempts * 100

                    onAddAttempt(Attempt(numAttempts, correctAttempts, accuracy))


                    timeIsUp = false
                    showResults = true
                }) { Text("OK") }
            },
            text = {
                OutlinedTextField(
                    value = playerGuess,
                    onValueChange = { playerGuess = it },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
            },

            )
    }





}