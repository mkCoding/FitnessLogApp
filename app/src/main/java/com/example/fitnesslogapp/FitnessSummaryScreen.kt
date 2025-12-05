package com.example.fitnesslogapp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.fitnesslogapp.nav.Route

@Composable
fun FitnessSummaryScreen(
    navController: NavController,
    name: String,
    duration:String,
    difficulty:String,
    calories:String
){
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Daily Summary",
            fontSize = 40.sp,
            modifier = Modifier.padding(16.dp)
        )

        // Exercise
        /*
        Must display:
        Title: "Daily Summary"
        Exercise: <name>
        Duration: <minutes>
        Difficulty: <difficulty>
        Calories: <calculated value>
        Color-highlighted Calories:
        Easy → Green
        Medium → Orange
        Hard → Red
        Include one button:
        "Add Another Entry" → Navigate back to the form
         */

        val difficultyColor =
            when(difficulty){
                "Easy" -> Color.Green
                "Medium"->Color(0xFFFFA500) // orange
                "Hard" -> Color.Red
                else -> Color.Black

            }


        Text("Exercise Name")
        Text("$name", fontSize = 30.sp, modifier = Modifier.padding(16.dp))

        Text("Duration")
        Text("$duration", fontSize = 30.sp, modifier = Modifier.padding(16.dp))

        Text("Difficulty")
        Text(text = "$difficulty",
            fontSize = 30.sp,
            modifier = Modifier.padding(16.dp),
            color = difficultyColor
        )

        Text("Calories")
        Text("$calories", fontSize = 30.sp, modifier = Modifier.padding(16.dp))

        // Button below
        OutlinedButton(
            onClick = { navController.navigate(Route.FITNESS_ENTRY_SCREEN)},
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(1.5.dp, Color.Blue)
        ) {
            Text(
                text = "Add Another Entry",
                modifier = Modifier.padding(vertical = 6.dp),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

enum class Difficulty(val label:String){
    EASY("Easy"),
    MEDIUM("Medium"),
    HARD("Hard"),
}

@Preview(showBackground = true)
@Composable
fun FitnessEntrySummaryScreenPreview(
){
    val navController = rememberNavController()
    FitnessSummaryScreen(navController = navController,
        "Pushups",
        "1 min",
        "Hard",
        calories = "100"
    )
}