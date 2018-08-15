package com.acme.tipcalculator.viewmodel

import android.app.Application
import android.databinding.ObservableField
import com.acme.tipcalculator.R
import com.acme.tipcalculator.model.RestaurantCalculator
import com.acme.tipcalculator.model.TipCalculation

class CalculatorViewModel @JvmOverloads constructor(
        app: Application, val calculator: RestaurantCalculator = RestaurantCalculator()) : ObservableViewModel(app) {

    val TAG = "CalculatorViewModel"
    var inputCheckAmount = ObservableField<String>("")
    var inputTipPercentage = ObservableField<String>("")

    var outputCheckAmount = ""
    var outputTipAmount = ""
    var outputTotalDollarAmount = ""

    init {
        updateOutputs(TipCalculation())
    }

    private fun updateOutputs(tc: TipCalculation) {
        outputCheckAmount = getApplication<Application>().getString(R.string.dollar_amount, tc.checkAmount)
        outputTipAmount = getApplication<Application>().getString(R.string.dollar_amount, tc.tipAmount)
        outputTotalDollarAmount = getApplication<Application>().getString(R.string.dollar_amount, tc.grandTotal)
    }

    fun calculateTip() {
        val checkAmount = inputCheckAmount.get()?.toDoubleOrNull()
        val tipPct = inputTipPercentage.get()?.toIntOrNull()

        if (checkAmount != null && tipPct != null) {
            updateOutputs(calculator.calculateTip(checkAmount, tipPct))
            clearInputs()
        }
    }

    fun clearInputs() {
        inputCheckAmount.set("0.00")
        inputTipPercentage.set("0")
        notifyChange()
    }


}