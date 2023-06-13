package com.example.accountsetting.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.accountsetting.model.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {

    private val _itemList = MutableStateFlow(listOf<Item>())
    val itemList : StateFlow<List<Item>> get() = _itemList

    private val _expandedCardIdsList = MutableStateFlow(listOf<Int>())
    val expandedCardIdsList: StateFlow<List<Int>> get() = _expandedCardIdsList

    private val _openBottomSheetList = MutableStateFlow(listOf<Int>())
    val openBottomSheetList: StateFlow<List<Int>> get() = _openBottomSheetList
//
//    private val _items = mutableStateListOf<Item>()
//    val items: List<Item> get() = _items

    init {
        getFakeData()
    }

    private fun getFakeData() {
        viewModelScope.launch {
            withContext(Dispatchers.Default){
                val testList = arrayListOf<Item>()
                testList.add(
                    Item( title = "Mobile no.",
                        detail = "9893839272",
                        icon = Icons.Default.Edit,
                        verified = false)
                )
                testList.add(
                    Item( title = "Email",
                        detail = "singh.damandeep@viewlift.com",
                        icon = Icons.Default.Edit,
                        verified = false)
                )
                testList.add(
                    Item( title = "Email",
                        detail = "ram@gmail.com",
                        icon = Icons.Default.Edit,
                        verified = true)
                )
                _itemList.emit(testList)
            }
        }
    }
//
//    private val _btnState = mutableStateOf(ButtonState())
//    val btnState: State<ButtonState> = _btnState
//
//    private val _bottomSheetState = mutableStateOf(ButtonState2())
//    val bottomSheetState: State<ButtonState2> = _bottomSheetState

//    fun addItem(
//        title: String,
//        detail: String,
//        icon: ImageVector,
//        verified: Boolean,
//    ) {
//        _items.add(
//            Item(
//                title = title,
//                detail = detail,
//                icon = icon,
//                verified = verified
//            )
//        )
//    }
//    fun updateItemText(index: Int, verified: Boolean) {
//        _items[index].verified = verified
//    }
//
//    fun updateDetailItem(index: Int, detail: String) {
//        _items[index].detail = detail
//    }
//    fun processBtnIntent(intent: ButtonIntent) {
//        when (intent) {
//            is ButtonIntent.Click -> bottomSheetState()
//            is ButtonIntent.OpenBottomSheet -> bottomSheetState()
//        }
//    }

//    private fun displayButton() {
//        val currentState = _btnState.value
//        _btnState.value = currentState.copy(isDisplayed = !currentState.isDisplayed)
//    }

//    private fun bottomSheetState() {
//        val currentState = _bottomSheetState.value
//        _bottomSheetState.value = currentState.copy(isDisplayed = !currentState.isDisplayed)
//    }


    fun onEditClicked(cardId :Int){
        _expandedCardIdsList.value = _expandedCardIdsList.value
            .toMutableList().also { list ->
            if (list.contains(cardId))list.remove(cardId) else list.add(cardId)
        }
    }
    fun onGetOtpClicked(cardId :Int){
        _openBottomSheetList.value = _openBottomSheetList.value
            .toMutableList().also { list ->
                if (list.contains(cardId))list.remove(cardId) else list.add(cardId)
            }
    }
}