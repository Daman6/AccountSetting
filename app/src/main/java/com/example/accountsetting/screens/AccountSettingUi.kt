package com.example.accountsetting.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.accountsetting.model.Item

//
//val itemList: MutableList<Item> =
//    mutableListOf(
////        Item(
////            title = "Full name",
////            detail = "Sanjeet Kumar Singh",
////            icon = Icons.Default.Edit,
////            verified = false
////        ),
////        Item(
////            title = "Mobile number",
////            detail = "+1(234)567-8901",
////            icon = Icons.Default.Edit,
////            verified = true
////        ),
//        Item(
//            title = "Mobile No.",
//            detail = "9953595635",
//            icon = Icons.Default.Edit,
//            verified = false
//        )
//    )

@Composable
fun AccountSettingUi(
    navController: NavController,
    viewModel: MainViewModel = viewModel()
) {
    val items by viewModel.itemList.collectAsState()
    val expandedCardIds by viewModel.expandedCardIdsList.collectAsState()
    val bottomsheetIds by viewModel.openBottomSheetList.collectAsState()


    Column(
        Modifier
            .wrapContentSize()
            .background(Color.Blue)
            .padding(10.dp)
    ) {
        Text(

            text = "Personal Details",
            color = Color.White,
            fontSize = 18.sp,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.heightIn(10.dp))
        LazyColumn {
            itemsIndexed(items) { index, data ->
                ItemUi(
                    index = index,
                    item = data,
                    expanded = expandedCardIds.contains(index),
                    open = bottomsheetIds.contains(index),
                    navController = navController
                )
            }
        }


    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemUi(
    viewModel: MainViewModel = viewModel(),
    index: Int,
    item: Item,
    expanded: Boolean,
    open: Boolean,
    navController: NavController
) {

    Log.e("kmdkm",index.toString())
    Column(
        Modifier
            .wrapContentSize()
            .background(Color.Blue)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (item.verified) {
                Text(
                    text = item.title + " (verified)",
                    color = Color.Gray,
                    fontSize = 14.sp,
                )
            } else {
                Text(
                    text = item.title,
                    color = Color.Gray,
                    fontSize = 14.sp,
                )
            }

            Icon(
                imageVector = item.icon,
                contentDescription = "icon",
                tint = Color.White,
                modifier = Modifier
                    .size(15.dp)
                    .clickable {
                        viewModel.onEditClicked(index)
                    }
            )


        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            var messageText by remember { mutableStateOf("") }
            val openBottomSheet = rememberModalBottomSheetState()
            Text(
                text = item.detail,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            AnimatedVisibility(expanded) {
                Button(
                    onClick = {
//                        viewModel.processBtnIntent(ButtonIntent.OpenBottomSheet)
                        viewModel.onGetOtpClicked(index)

                    },
                    modifier = Modifier
                        .wrapContentWidth()
                        .height(35.dp),
                    shape = RectangleShape
                ) {
                    Text(
                        text = "GET OTP",
                        color = Color.White,
                        fontSize = 5.sp
                    )

                }

            }

            if (open) {
                ModalBottomSheet(
                    onDismissRequest = {
//                        viewModel.processBtnIntent(ButtonIntent.OpenBottomSheet)
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

                        val textValue = "Verify Your ${viewModel.itemList.value[index].title}"
                        val waringText =
                            "We Have Sent a verification code to ${viewModel.itemList.value[index].detail} \n Please enter the code below to verify your mobile number."

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
                                    if (item.verified) {
                                        messageText = ""
                                        navController.navigate(Screen.AddNewMobileScreen.route + "/${item.title}" + "/$index")
                                    } else {
                                        viewModel.itemList.value[index].verified = true
                                        messageText = ""
//                                        viewModel.processBtnIntent(ButtonIntent.OpenBottomSheet)
                                        viewModel.onGetOtpClicked(index)
                                    }
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

        Divider(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp)
                .height(1.dp)
                .background(Color.White)
        )


    }
}

@Preview
@Composable
fun ItemUiPrev() {
    AccountSettingUi(navController = rememberNavController())
}