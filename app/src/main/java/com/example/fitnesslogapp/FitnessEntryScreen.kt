package com.example.fitnesslogapp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.fitnesslogapp.nav.Route

/*
Design a simple two-screen fitness logging app with
input validation, conditional UI, and summary display.
Users should be able to:

Enter a fitness log (exercise name, duration, difficulty),
2.Validate the inputs
3.Compute calories burned (simple formula)
4.Navigate to a summary screen
5.Display all details + computed result
:thumbsup:


 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FitnessEntryScreen(
    navController: NavController
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Fitness Entry",
            fontSize = 40.sp,
            modifier = Modifier.padding(16.dp)
        )

        // Exercise name, duration and difficulty

        // User entered excercise name
        var excerciseName by remember { mutableStateOf("") }

        // Drop down duration menu
        val durationOptions = listOf(
            "1 min","2 min","3 mins","4 mins", "5 mins",
            "6 min","7 min","8 mins","9 mins", "10 mins"
        )
        var durationExpanded by remember {mutableStateOf(false)}
        var selectedDuration by remember{mutableStateOf(durationOptions[0])}

        // Drop down difficulty (Easy, Medium, Hard)
        val difficultyOptions = listOf("Easy", "Medium", "Hard")
        val difficultyExpanded by remember {mutableStateOf(false)}
        var selectedDifficulty by remember { mutableStateOf(difficultyOptions[0]) }

        // difficulty map
        val difficultyMap = mapOf(
            "Easy" to 4,
            "Medium" to 6,
            "Hard" to 8
        )


        TextField(
            value = excerciseName,
            onValueChange = {excerciseName = it},
            placeholder = {
                Text(
                    color = Color.Gray,
                    text ="Exercise Name",
                    fontStyle = FontStyle.Italic)
            }
        )

        // Duration dropdown
        DurationDropdown(
            selectedDuration = selectedDuration,
            onSelected = {selectedDuration = it},
            durationOptions=durationOptions

        )
        DifficultyDropdown(
            selectedDifficulty = selectedDifficulty,
            onSelected = { selectedDifficulty = it },
            difficultyOptions = difficultyOptions
        )

        val minutes = selectedDuration.filter { it.isDigit() }.toInt()
        val calories = minutes * (difficultyMap[selectedDifficulty] ?: 0)

        OutlinedButton(
            enabled = excerciseName.isNotEmpty(),
            onClick = {
                navController.navigate(
                    Route.SUMMARY +
                            "?name=${excerciseName}" +
                            "&duration=${selectedDuration}" +
                            "&difficulty=${selectedDifficulty}" +
                            "&calories=${calories}"
                )
            },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(1.5.dp, Color.Blue)
        ) {
            Text(
                text = "Create Summary",
                modifier = Modifier.padding(vertical = 6.dp),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

fun calculateCalories(){

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DifficultyDropdown(
    selectedDifficulty: String,
    onSelected: (String) -> Unit,
    difficultyOptions: List<String>
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        TextField(
            value = selectedDifficulty,
            onValueChange = {},
            readOnly = true,
            label = { Text("Select a difficulty") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
            modifier = Modifier.menuAnchor(MenuAnchorType.PrimaryEditable)
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            difficultyOptions.forEach { item ->
                DropdownMenuItem(
                    text = { Text(item) },
                    onClick = {
                        onSelected(item)
                        expanded = false
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DurationDropdown(
    selectedDuration: String,
    onSelected: (String) -> Unit,
    durationOptions: List<String>
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        TextField(
            value = selectedDuration,
            onValueChange = {},
            readOnly = true,
            label = { Text("Select a duration") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
            modifier = Modifier.menuAnchor(MenuAnchorType.PrimaryEditable)
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            durationOptions.forEach { item ->
                DropdownMenuItem(
                    text = { Text(item) },
                    onClick = {
                        onSelected(item)
                        expanded = false
                    }
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun FitnessEntryScreenPreview(){
    val navController = rememberNavController()
    FitnessEntryScreen(navController)
}