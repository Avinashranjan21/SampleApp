package com.example.sampleapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sampleapp.home.AddMoneyScreen
import com.example.sampleapp.home.HomeScreen
import com.example.sampleapp.home.MakePaymentScreen
import com.example.sampleapp.login.LoginScreen
import com.example.sampleapp.transaction.TransactionHistoryScreen

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Home : Screen("home")
    object AddMoney : Screen("add_money")
    object MakePayment : Screen("make_payment")
    object ShowTransaction : Screen("transaction_history_screen")
}

@Composable
fun AppNavHost(navController: NavHostController, startDestination: String) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(Screen.Login.route) { LoginScreen(navController) }
        composable(Screen.Home.route) { HomeScreen(navController) }
        composable(Screen.AddMoney.route) { AddMoneyScreen(navController) }
        composable(Screen.MakePayment.route) { MakePaymentScreen(navController) }
        composable(Screen.ShowTransaction.route) { TransactionHistoryScreen(navController) }
    }
}