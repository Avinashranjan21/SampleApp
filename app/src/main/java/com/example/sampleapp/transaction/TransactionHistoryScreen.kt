package com.example.sampleapp.transaction

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sampleapp.home.PaymentViewModel
import com.example.sampleapp.home.TransactionType

@Composable
fun TransactionHistoryScreen(navController: NavController) {
    val viewModel: PaymentViewModel = hiltViewModel()
    val transactionsState = viewModel.transactions.collectAsState()
    val transactions = transactionsState.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Transaction History",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (transactions.isEmpty()) {
            Text(text = "No transactions available.", fontSize = 18.sp)
        } else {
            LazyColumn {
                items(transactions.size) { index ->
                    val transaction = transactions[index]
                    Text(
                        text = "${transaction.date}: ${if (transaction.type == TransactionType.ADD) "+" else "-"}₹${transaction.amount}",
                        color = if (transaction.type == TransactionType.ADD) Color.Green else Color.Red,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
            }
        }

        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = "Back")
        }
    }
}