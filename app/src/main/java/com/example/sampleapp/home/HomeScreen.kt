package com.example.sampleapp.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    val viewModel: PaymentViewModel = hiltViewModel()
    val amountState = viewModel.amount.collectAsState()
    val showAmountState = viewModel.showAmountState.collectAsState()

    val amount = amountState.value
    val showAmount = showAmountState.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Toggle and Amount
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (showAmount) "₹${amount}" else "₹XX.XX",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
            Switch(
                checked = showAmount,
                onCheckedChange = { viewModel.toggleShowAmount() }
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Buttons
        Button(
            onClick = { navController.navigate("add_money") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Add Money")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navController.navigate("make_payment") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Make Payment")
        }
    }
}