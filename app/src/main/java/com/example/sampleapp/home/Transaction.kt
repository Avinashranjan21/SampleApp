package com.example.sampleapp.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Transaction(
    val amount: Double,
    val type: TransactionType,
    val date: String
)

enum class TransactionType {
    ADD, SUBTRACT
}

@Composable
fun TransactionItem(transaction: Transaction) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(
                if (transaction.type == TransactionType.ADD) Color(0xFFE6F9EA) else Color(
                    0xFFFDE6E6
                ),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = transaction.date,
            fontSize = 14.sp,
            color = Color.Gray
        )
        Text(
            text = "${if (transaction.type == TransactionType.ADD) "+" else "-"}₹${transaction.amount}",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = if (transaction.type == TransactionType.ADD) Color(0xFF28A745) else Color(
                0xFFFF4D4D
            )
        )
    }
}