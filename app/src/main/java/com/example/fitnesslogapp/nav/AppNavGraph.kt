package com.example.fitnesslogapp.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.fitnesslogapp.FitnessEntryScreen
import com.example.fitnesslogapp.FitnessSummaryScreen

@Composable
fun AppNavGraph(
    navController:NavHostController
) {

   NavHost(navController = navController, startDestination = Route.FITNESS_ENTRY_SCREEN) {
       composable(route = Route.FITNESS_ENTRY_SCREEN){
           //composable screen
           FitnessEntryScreen(navController)
       }
       composable(
           route = Route.SUMMARY+
                   "?name={name}&duration={duration}&difficulty={difficulty}&calories={calories}",
           arguments = listOf(
               // argument is string
               navArgument("name"){type = NavType.StringType},
               navArgument("duration"){type = NavType.StringType},
               navArgument("difficulty"){type = NavType.StringType},
               navArgument("calories"){type = NavType.StringType}
           )
       ){ backStackEntry ->

           // grab value passed from previous screen in a variable
           val exerciseName = backStackEntry.arguments?.getString("name") ?: ""
           val duration = backStackEntry.arguments?.getString("duration")?:""
           val difficulty = backStackEntry.arguments?.getString("difficulty")?:""
           val calories = backStackEntry.arguments?.getString("calories")?:""

           FitnessSummaryScreen(
               navController = navController,
               name = exerciseName,
               duration = duration,
               difficulty = difficulty,
               calories = calories
           )
       }

    }

}

object Route {
    const val FITNESS_ENTRY_SCREEN = "fitnessEntryScreen"

    /*
    ScreenName
    Arguments will be placed after "?"
    multiple arguments will be attached with &
     */
   const val SUMMARY = "fitnessSummaryScreen"

}