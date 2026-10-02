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


@Composable
fun attemptScreen(
    attempts: List<Attempt>,
    onAddAttempt: (Attempt) -> Unit,
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    var newAttempt by remember { mutableStateOf(1) }
    var newCorrectAttempt by remember { mutableStateOf(1) }
    var newAccuracy by remember { mutableStateOf(1.0f) }
    var selectedAttempt by remember { mutableStateOf<Attempt?>(null) }




    Column(modifier = modifier.fillMaxSize()) {
        Row(
            modifier = Modifier.
            fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Start
        ){
            Button(
                onClick = {
                    navController.popBackStack()
            }, modifier = Modifier
            ) {
                Text(text = "Back")
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ){}

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
            Text(
                text = "Attempts     ",
                fontSize = 18.sp
            )

            Text(
                text = "Correct Attempts     ",
                fontSize = 18.sp
            )

            Text(
               "Accuracy     ",
                fontSize = 18.sp
            )



        }


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(25.dp)
        ) {
            Spacer(modifier = Modifier.width(8.dp))



        }



        LazyColumn(modifier = Modifier.fillMaxSize()) {
            itemsIndexed(attempts) { index, attempt ->
                CityRow(attempt = attempt, savedAttempt = { selectedAttempt = it })

                if (index < attempts.lastIndex) {
                    HorizontalDivider()
                }
            }
        }
    }
}

@Composable
fun CityRow(attempt: Attempt, savedAttempt: (Attempt) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        Text(
            text = attempt.numAttempt.toString(),
            fontSize = 30.sp,
            modifier = Modifier.weight(1f)
        )

        Text(
            text = attempt.correctAttempt.toString(),
            fontSize = 30.sp,
            modifier = Modifier.weight(1f)
        )

        Text(
            text = attempt.accuracy.toString(),
            fontSize = 30.sp,
            modifier = Modifier.weight(1f)
        )



    }
}

@Preview(showBackground = true)
@Composable
fun AttemptScreenPreview() {
    OktemezaRapidRecallTheme() {
        val navController = rememberNavController()
        attemptScreen(
           attempts = listOf(
               Attempt(1, 1, 0.0f)
           ),
            onAddAttempt = {},
            modifier = Modifier,
            navController)

    }
}