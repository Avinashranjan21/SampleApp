package com.example.sampleapp.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sampleapp.navigation.Screen

@Composable
fun HomeScreen(navController: NavController) {
    val viewModel: PaymentViewModel = hiltViewModel()

    val amountState = viewModel.amount.collectAsState()
    val transactionsState = viewModel.transactions.collectAsState()
    val showAmountState = viewModel.showAmountState.collectAsState()

    val amount = amountState.value
    val showAmount = showAmountState.value
    val transactions = transactionsState.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Top row with toggle button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Payment App", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Switch(
                checked = showAmount,
                onCheckedChange = { viewModel.toggleShowAmount() }
            )
        }

        // Display the amount if showAmount is true
        if (showAmount) {
            Text(
                text = "₹$amount",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 16.dp)
            )
        } else {
            Text(
                text = "₹XX.xx",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 16.dp)
            )
        }

        // Last 5 transactions
        if (transactions.isNotEmpty()) {
            Text(
                text = "Last Transactions:",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            transactions.takeLast(5).forEach { transaction ->
                Text(
                    text = "${transaction.date}: ${if (transaction.type == TransactionType.ADD) "+" else "-"}₹${transaction.amount}",
                    color = if (transaction.type == TransactionType.ADD) Color.Green else Color.Red,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(vertical = 4.dp).fillMaxWidth()
                )
            }

            // Show More Button
            Button(
                onClick = { navController.navigate(Screen.ShowTransaction.route) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text(text = "Show More Transactions")
            }
        }

        // Buttons for Make Payment and Add Money
        Button(
            onClick = {
                navController.navigate(Screen.AddMoney.route)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(text = "Add Money")
        }

        Button(
            onClick = {
                navController.navigate(Screen.MakePayment.route)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(text = "Make Payment")
        }
    }
}