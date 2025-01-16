package com.example.sampleapp.home

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PaymentRepository @Inject constructor() {
    private val _amount = MutableStateFlow(0.0)
    val amount: StateFlow<Double> get() = _amount

    private val _transactions = MutableStateFlow<List<Transaction>>(emptyList())
    val transactions: StateFlow<List<Transaction>> = _transactions

    fun addAmount(value: Double) {
        _amount.value += value
        recordTransaction(value, TransactionType.ADD)
    }

    fun subtractAmount(value: Double): Boolean {
        return if (_amount.value >= value) {
            _amount.value -= value
            recordTransaction(value, TransactionType.SUBTRACT)
            true
        } else {
            false
        }
    }
    private fun recordTransaction(amount: Double, type: TransactionType) {
        val newTransaction = Transaction(
            amount = amount,
            type = type,
            date = getCurrentDateTime()
        )
        _transactions.value = _transactions.value.toMutableList().apply { add(newTransaction) }
    }

    private fun getCurrentDateTime(): String {
        val dateFormat = SimpleDateFormat("EEEE, MMMM d, yyyy 'at' h:mm a", Locale.getDefault())
        return dateFormat.format(Date())
    }
}