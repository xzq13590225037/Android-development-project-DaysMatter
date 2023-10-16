package com.leaf3stones.daysmaster

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.leaf3stones.daysmaster.detail.DetailScreen
import com.leaf3stones.daysmaster.edit.EditScreen
import com.leaf3stones.daysmaster.home.HomeScreen

@Composable
fun DaysMasterApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "homeScreen") {
        composable("homeScreen") {
            HomeScreen(onAddNewEventButtonClick = {
                navController.navigate("editScreen/-1")
            }, onGoDetailButtonClicked = { id ->
                navController.navigate("detailScreen/$id")
            })
        }

        composable(
            "detailScreen/{eventId}",
            arguments = listOf(navArgument("eventId") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments!!.getInt("eventId")
            DetailScreen(id, onGoBackButton = {
                navController.navigateUp()
            }, onEditButtonClicked = {
                navController.navigate("editScreen/$id")
            })
        }

        composable(
            "editScreen/{eventId}",
            arguments = listOf(navArgument("eventId") { type = NavType.IntType })
        ) { backStackEntry ->
            EditScreen(backStackEntry.arguments!!.getInt("eventId"), goBack = {
                navController.navigateUp()
            })
        }
    }
}