package com.example.razorpayapitast

data class ItemUpdateModel(
    val amount: Int,
    val currency: String,
    val description: String,
    val name: String
)