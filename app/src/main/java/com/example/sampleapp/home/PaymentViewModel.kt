package com.example.sampleapp.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(
    private val repository: PaymentRepository
) : ViewModel() {

    val amount: StateFlow<Double> get() = repository.amount
    val transactions: StateFlow<List<Transaction>> = repository.transactions

    private val _showAmountState = MutableStateFlow(true)
    val showAmountState: StateFlow<Boolean> get() = _showAmountState

    fun toggleShowAmount() {
        _showAmountState.value = !_showAmountState.value
    }

    fun addAmount(value: Double) = repository.addAmount(value)

    fun subtractAmount(value: Double): Boolean = repository.subtractAmount(value)
}