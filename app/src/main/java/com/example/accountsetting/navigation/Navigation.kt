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
import com.example.accountsetting.screens.VerifyScreenUi

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.AccountSetting.route ){
        composable(route = Screen.AccountSetting.route){
            AccountSettingUi(navController)
        }
        composable(
            route= Screen.VerifyScreen.route + "/{title}/{details}/{verified}",
            arguments = listOf(
                navArgument("title"){
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                },
                navArgument("details"){
                    type = androidx.navigation.NavType.StringType
                    defaultValue = ""
                    nullable = true
                },
                navArgument("verified"){
                    type = androidx.navigation.NavType.BoolType
                    defaultValue = false
                    nullable = false
                }
            )
        ){entry->
            entry.arguments?.getString("title")?.let {title->
                entry.arguments?.getString("details")?.let {details->
                    entry.arguments?.getBoolean("verified")?.let { verified ->
                        VerifyScreenUi(
                            navController = navController,
                            title = title,
                            details = details,
                            verified = verified
                        )
                    }
                }
            }
        }
        composable(route= Screen.AddNewMobileScreen.route){
            AddNewMobileUi(navController)
        }
    }
}