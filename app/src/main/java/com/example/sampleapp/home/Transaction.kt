package com.example.sampleapp.home

data class Transaction(
    val amount: Double,
    val type: TransactionType,
    val date: String
)

enum class TransactionType {
    ADD, SUBTRACT
}