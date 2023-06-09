package com.example.accountsetting.screens

import android.icu.text.CaseMap.Title
import android.telecom.Call.Details
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


@Composable
fun VerifyScreenUi(
    navController: NavController,
    title: String?,
    details: String?,
    verified: Boolean?,
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.Blue),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Verify Your $title",
            color = Color.White,
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "We Have sent a verification code to $details",
            color = Color.White,
            textAlign = TextAlign.Center,
            fontSize = 12.sp,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))


        Button(
            onClick = {
                Log.e("ndjnd", navController.previousBackStackEntry?.destination?.route.toString())
                if (navController.previousBackStackEntry?.destination?.route.toString()
                        .equals(Screen.AddNewMobileScreen.route)
                ) {
                    navController.navigate(Screen.AccountSetting.route)
                } else if (navController.previousBackStackEntry?.destination?.route.toString()
                        .equals(Screen.AccountSetting.route) && !verified!!
                ) {
                    navController.navigate(Screen.AccountSetting.route)
                } else {
                    navController.navigate(Screen.AddNewMobileScreen.route)
                }
            },
            modifier = Modifier
                .wrapContentSize(),
            shape = RectangleShape
        ) {
            Text(
                text = "Submit",
                color = Color.White,
                fontSize = 12.sp
            )

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewMobileUi(
    navController: NavController
) {
    val text = remember {
        mutableStateOf("")
    }
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.Blue),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Add your new mobile number",
            color = Color.White,
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = text.value,
            onValueChange = {
                text.value = it
            },
            placeholder = {
                Text(
                    "enter no.",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W400,
                    textAlign = TextAlign.Start,
                )
            },
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth(),
            textStyle = TextStyle(Color.White),
            singleLine = true,
        )
        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                navController.navigate(Screen.VerifyScreen.route + "/${"Mobile Number"}/${text.value}/${true}")
            },
            modifier = Modifier
                .fillMaxWidth(),
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(Color.Red)
        ) {
            Text(
                text = "Get Verification Code",
                color = Color.White,
                fontSize = 12.sp
            )

        }
    }
}


@Preview
@Composable
fun VerifyScreenPrev() {
    AddNewMobileUi(navController = rememberNavController())
}