package com.example.accountsetting.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.accountsetting.screens.AccountSettingUi
import com.example.accountsetting.screens.AddNewMobileUi
import com.example.accountsetting.screens.Screen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.AccountSetting.route) {
        composable(route = Screen.AccountSetting.route) {
            AccountSettingUi(navController)
        }
        composable(
            route = Screen.AddNewMobileScreen.route + "/{title}/{index}",
            arguments = listOf(
                navArgument("title") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                },
                navArgument("index") {
                    type = androidx.navigation.NavType.IntType
                    defaultValue = 0
                    nullable = false
                }
            )
        ) { entry ->
            entry.arguments?.getString("title")?.let { title ->
                entry.arguments?.getInt("index")?.let { index ->
                    AddNewMobileUi(
                        navController = navController,
                        title = title,
                        index = index
                    )
                }
            }
        }
    }
}
