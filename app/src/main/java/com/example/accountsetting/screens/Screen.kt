package com.example.accountsetting.screens

import androidx.navigation.NavArgs

sealed class Screen(
    val route: String
){
    object AccountSetting: Screen("accountSetting_screen")
    object VerifyScreen: Screen("verify_screen")
    object AddNewMobileScreen: Screen("addNewMobile_screen")



}
