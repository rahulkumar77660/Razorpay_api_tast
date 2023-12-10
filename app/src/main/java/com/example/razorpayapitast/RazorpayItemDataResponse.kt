package com.example.razorpayapitast

data class RazorpayItemDataResponse(
    val count: Int,
    val entity: String,
    val items: List<Item>
)