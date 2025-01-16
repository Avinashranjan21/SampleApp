package com.example.sampleapp.home

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PaymentRepository @Inject constructor() {
    private val _amount = MutableStateFlow(0.0)
    val amount: StateFlow<Double> get() = _amount

    /**
     * Adds the specified amount to the current total.
     *
     * @param value The amount to add.
     */
    fun addAmount(value: Double) {
        _amount.value += value
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
            true
        } else {
            false
        }
    }
}