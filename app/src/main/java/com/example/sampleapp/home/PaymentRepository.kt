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

    /**
     * Adds the specified amount to the current total.
     *
     * @param value The amount to add.
     */
    fun addAmount(value: Double) {
        _amount.value += value
        recordTransaction(amount.value, TransactionType.ADD)
    }

    /**
     * Subtracts the specified amount from the current total if possible.
     *
     * @param value The amount to subtract.
     * @return `true` if the subtraction was successful, `false` if the balance is insufficient.
     */
    fun subtractAmount(value: Double): Boolean {
        return if (_amount.value >= value) {
            _amount.value -= value
            recordTransaction(amount.value, TransactionType.SUBTRACT)
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
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return dateFormat.format(Date())
    }
}