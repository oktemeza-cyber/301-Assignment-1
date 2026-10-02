package com.example.oktemeza_rapidrecall.ui.theme.ui.theme
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.FloatingActionButton
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.draw.clip
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.oktemeza_rapidrecall.Log
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun logScreen(
    logs: List<Log>,
    onAddLog: (Log) -> Unit,
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    var newLength by remember { mutableStateOf(1) }
    var newUserInput by remember { mutableStateOf(1) }
    var newTargetSequence by remember { mutableStateOf(1) }
    var newCorrectness by remember { mutableStateOf(false) }
    var newTimeStamp by remember { mutableStateOf(1) }
    var selectedLog by remember { mutableStateOf<Log?>(null) }


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
            horizontalArrangement = Arrangement.Start
        ){

            Text(
                text = "Length     ",
                fontSize = 18.sp
            )

            Text(
                text = "User Input     ",
                fontSize = 18.sp
            )

            Text(
                "Target Seq    ",
                fontSize = 18.sp
            )

            Text(
                text = " Correct? ",
                fontSize = 18.sp
            )


        }


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Spacer(modifier = Modifier.width(8.dp))



            }








        LazyColumn(modifier = Modifier.fillMaxSize()) {
            itemsIndexed(logs) { index, log ->
                CityRow(log = log, savedLog = { selectedLog = it })

                if (index < logs.lastIndex) {
                    HorizontalDivider()
                }
            }
        }
    }
}

@Composable
fun CityRow(log: Log, savedLog: (Log) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        Text(
            text = log.length.toString() + "   ",
            fontSize = 24.sp,
            modifier = Modifier.weight(0.45f)
        )

        Text(
            text = log.userInput.toString() + "   ",
            fontSize = 24.sp,
            modifier = Modifier.weight(0.8f)
        )

        Text(
            text = log.targetSequence.toString()+ "   ",
            fontSize = 24.sp,
            modifier = Modifier.weight(0.8f)
        )

        Text(
            text = log.correctness.toString()+ "   ",
            fontSize = 24.sp,
            modifier = Modifier.weight(0.5f)
        )


    }

    Text(
        text = SimpleDateFormat("MMM d, yyyy  h:mm:ss a", Locale.getDefault())
            .format(Date(log.timeStamp * 1000L)),
        fontSize = 16.sp,
        modifier = Modifier.padding(top = 4.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun LogScreenPreview() {
    OktemezaRapidRecallTheme() {
        val navController = rememberNavController()
        logScreen(
            logs = listOf(
                Log("0", "0", "0", false, 1)
            ),
            onAddLog = {},
            modifier = Modifier,
            navController)

    }
}