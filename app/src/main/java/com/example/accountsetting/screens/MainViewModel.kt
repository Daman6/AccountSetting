package com.example.accountsetting.screens

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.accountsetting.intents.ButtonIntent
import com.example.accountsetting.states.ButtonState

class MainViewModel : ViewModel() {


    private val _btnState = mutableStateOf(ButtonState())
    val btnState : State<ButtonState> = _btnState

    fun processBtnIntent(intent: ButtonIntent){
        when(intent){
            is ButtonIntent.Click -> displayButton()
        }
    }

    private fun displayButton() {
        val currentState = _btnState.value
        _btnState.value = currentState.copy(isDisplayed = !currentState.isDisplayed)
    }
}