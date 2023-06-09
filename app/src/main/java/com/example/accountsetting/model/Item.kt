package com.example.accountsetting.model

import androidx.compose.ui.graphics.vector.ImageVector

data class Item(
    val title: String,
    val detail: String,
    val icon: ImageVector,
    val verified: Boolean,

)
