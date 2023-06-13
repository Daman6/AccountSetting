package com.example.accountsetting.model

import androidx.compose.ui.graphics.vector.ImageVector

data class Item(
    var title: String,
    var detail: String,
    var icon: ImageVector,
    var verified: Boolean,
)
