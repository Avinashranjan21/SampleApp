package com.example.sampleapp.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sampleapp.navigation.Screen

@Composable
fun HomeScreen(navController: NavController, viewModel: PaymentViewModel = hiltViewModel()) {

    val amountState = viewModel.amount.collectAsState()
    val transactionsState = viewModel.transactions.collectAsState()
    val transactions = transactionsState.value
    val amount = amountState.value
    val showAmountState = remember { mutableStateOf(true) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF00C6FF), Color(0xFF0072FF))
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Header with balance and toggle
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (showAmountState.value) "₹$amount" else "₹XXX.XX",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                )
                IconButton(
                    onClick = { showAmountState.value = !showAmountState.value },
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color.White, CircleShape)
                ) {
                    Text(
                        text = if (showAmountState.value) "Hide" else "Show",
                        color = Color(0xFF0072FF),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // Action buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { navController.navigate(Screen.AddMoney.route) },
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                    colors = ButtonDefaults.buttonColors(contentColor = Color(0xFF28A745))
                ) {
                    Text(text = "Add Money", color = Color.White, fontWeight = FontWeight.Bold)
                }
                Button(
                    onClick = { navController.navigate(Screen.MakePayment.route) },
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp),
                    colors = ButtonDefaults.buttonColors(contentColor = Color(0xFFFF4D4D))
                ) {
                    Text(text = "Make Payment", color = Color.White, fontWeight = FontWeight.Bold)
                }
            }

            // Recent Transactions
            Text(
                text = "Recent Transactions",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Color.White,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(8.dp)
                    .weight(1f)
            ) {
                if (transactions.isNotEmpty()) {
                    items(transactions.size) { index ->
                        val transaction = transactions[index]
                        TransactionItem(transaction)
                    }
                } else {
                    item {
                        Text(
                            text = "No transactions yet.",
                            fontSize = 16.sp,
                            color = Color.Gray,
                            modifier = Modifier.padding(16.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

            // Show More Button
            if (transactions.isNotEmpty()) {
                Button(
                    onClick = { navController.navigate(Screen.ShowTransaction.route) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    colors = ButtonDefaults.buttonColors(contentColor = Color(0xFF0072FF))
                ) {
                    Text(text = "Show More", color = Color.White, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}