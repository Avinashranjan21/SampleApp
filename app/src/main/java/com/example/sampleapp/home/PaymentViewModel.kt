package com.example.sampleapp.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor() : ViewModel() {

    private val _amount = MutableStateFlow(0.0)
    val amount: StateFlow<Double> = _amount

    private val _showAmount = MutableStateFlow(true)
    val showAmount: StateFlow<Boolean> = _showAmount

    fun toggleAmountVisibility() {
        _showAmount.value = !_showAmount.value
    }

    fun addMoney(amountToAdd: Double) {
        _amount.value += amountToAdd
    }

    fun makePayment(amountToDeduct: Double): Boolean {
        return if (_amount.value >= amountToDeduct) {
            _amount.value -= amountToDeduct
            true
        } else {
            false
        }
    }
}