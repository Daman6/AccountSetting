package com.example.accountsetting.screens

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.accountsetting.intents.ButtonIntent
import com.example.accountsetting.model.Item
import com.example.accountsetting.states.ButtonState


val itemList: MutableList<Item> =
    mutableListOf(
//        Item(
//            title = "Full name",
//            detail = "Sanjeet Kumar Singh",
//            icon = Icons.Default.Edit,
//            verified = false
//        ),
//        Item(
//            title = "Mobile number",
//            detail = "+1(234)567-8901",
//            icon = Icons.Default.Edit,
//            verified = true
//        ),
        Item(
            title = "Email",
            detail = "sanjeetksingh@viewlift.com",
            icon = Icons.Default.Edit,
            verified = true
        )
    )

@Composable
fun AccountSettingUi(
    navController: NavController,
    ) {
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
            items(itemList) {
                ItemUi(
                    title = it.title,
                    details = it.detail,
                    icon = it.icon,
                    verified = it.verified,
                    navController = navController
                )
            }
        }

    }
}

@Composable
fun ItemUi(
    viewModel: MainViewModel = viewModel(),
    title: String,
    details: String,
    icon: ImageVector,
    verified: Boolean,
    navController: NavController
) {
//    val btnDisplay = remember {
//        mutableStateOf(false)
//    }

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
            if (verified) {
            Text(
                text = title + " (verified)",
                color = Color.Gray,
                fontSize = 14.sp,
            )
            } else {
                Text(
                    text = title,
                    color = Color.Gray,
                    fontSize = 14.sp,
                )
            }
            if (verified) {
                Icon(
                    imageVector = icon,
                    contentDescription = "icon",
                    tint = Color.White,
                    modifier = Modifier
                        .size(15.dp)
                        .clickable {
//                            btnDisplay.value = !btnDisplay.value
                            viewModel.processBtnIntent(ButtonIntent.Click)
                        }
                )
            }

        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = details,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

                AnimatedVisibility(viewModel.btnState.value.isDisplayed || !verified) {
                    Button(
                        onClick = {
                                  navController.navigate(Screen.VerifyScreen.route + "/${title}/${details}/${verified}")
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