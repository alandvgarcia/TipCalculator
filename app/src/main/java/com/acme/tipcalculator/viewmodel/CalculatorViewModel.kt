package com.acme.tipcalculator.viewmodel

import android.databinding.BaseObservable
import android.databinding.ObservableField
import android.util.Log
import com.acme.tipcalculator.model.RestaurantCalculator
import com.acme.tipcalculator.model.TipCalculation

class CalculatorViewModel(val calculator: RestaurantCalculator = RestaurantCalculator()) : BaseObservable() {

    val TAG = "CalculatorViewModel"
    var inputCheckAmount = ObservableField<String>("")
    var inputTipPercentage = ObservableField<String>("")
    var tipCalculation = TipCalculation()

    fun calculateTip() {
        Log.d(TAG, "calculateTipInvoked")

        val checkAmount = inputCheckAmount.get()?.toDoubleOrNull()
        val tipPct = inputTipPercentage.get()?.toIntOrNull()

        if (checkAmount != null && tipPct != null) {
            Log.d(TAG, "CheckAmount $checkAmount, TipPercentage $tipPct")
            tipCalculation = calculator.calculateTip(checkAmount, tipPct)
            clearInputs()
        }
    }

    fun clearInputs() {
        inputCheckAmount.set("0.00")
        inputTipPercentage.set("0")
        notifyChange()
    }


}