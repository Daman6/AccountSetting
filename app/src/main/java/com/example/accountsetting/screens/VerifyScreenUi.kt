package com.example.accountsetting.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewMobileUi(
    navController: NavController,
    viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    title: String?,
    index: Int
) {
    val text = remember {
        mutableStateOf("")
    }
    var messageText by remember { mutableStateOf("") }

    val openBottomSheet = rememberModalBottomSheetState()

    val bottomsheetIds by viewModel.openBottomSheetList.collectAsState()


    Column(
        Modifier
            .wrapContentSize()
            .background(Color.Blue),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Add your new $title",
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
                    "",
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
                viewModel.onGetOtpClicked(index)
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
        if (bottomsheetIds.contains(index)) {
            ModalBottomSheet(
                onDismissRequest = {
                    viewModel.onGetOtpClicked(index)
                },
                sheetState = openBottomSheet,
                containerColor = Color.Blue,

                ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 64.dp)
                ) {

                    val textValue = "Verify Your "
                    val waringText =
                        "We Have Sent a verification code to ${text.value} \n Please enter the code below to verify your mobile number."

                    Text(
                        text = textValue,
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.W700,
                        color = Color.White,
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = waringText,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.W400,
                        fontSize = 14.sp,
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = messageText,
                        onValueChange = {
                            messageText = it
                            if (it.length == 5) {
                                viewModel.itemList.value[index].verified = true
                                viewModel.itemList.value[index].detail = text.value
                                messageText = ""
                                viewModel.onGetOtpClicked(index)
                                navController.navigate(Screen.AccountSetting.route)
                                Log.e("TAG", "AddNewMobileUi: $", )


                            }
                        },
                        placeholder = {
                            Text(
                                "Enter Code",
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
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent,
                            cursorColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                        ),
                    )
                    Spacer(modifier = Modifier.height(10.dp))


                    Text(
                        color = Color.White,
                        text = "Resend Code",
                        textAlign = TextAlign.Center,
                        textDecoration = TextDecoration.Underline,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W400,
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        color = Color.White,
                        text = "Use a Different Mobile Number",
                        textDecoration = TextDecoration.Underline,
                        textAlign = TextAlign.Center,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W400
                    )

                }
            }
        }
    }
}


@Preview
@Composable
fun VerifyScreenPrev() {
//    AddNewMobileUi(navController = rememberNavController())
}