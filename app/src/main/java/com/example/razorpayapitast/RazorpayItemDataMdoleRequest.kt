package com.example.razorpayapitast

data class RazorpayItemDataMdoleRequest(
    val amount: Int,
    val currency: String,
    val description: String,
    val name: String
)