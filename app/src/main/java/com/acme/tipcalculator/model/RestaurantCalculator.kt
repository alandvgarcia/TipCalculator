package com.acme.tipcalculator.model

import android.arch.lifecycle.LiveData
import java.math.RoundingMode

class RestaurantCalculator(val repository: TipCalculationRepository = TipCalculationRepository()) {
    fun calculateTip(checkAmount: Double, tipPct: Int): TipCalculation {

        val tipAmount = (checkAmount * (tipPct.toDouble() / 100.0))
                .toBigDecimal()
                .setScale(2, RoundingMode.HALF_UP)
                .toDouble()


        val grandTotal = checkAmount + tipAmount

        return TipCalculation(
                checkAmount = checkAmount,
                tipPct = tipPct,
                tipAmount = tipAmount,
                grandTotal = grandTotal
        )
    }

    fun saveCalculation(tc: TipCalculation) {
        repository.saveCalculation(tc)
    }

    fun loadTipCalculationByName(locationName: String): TipCalculation? {
        return repository.loadTipCalculationByName(locationName)
    }

    fun loadSavedTipCalculations(): LiveData<List<TipCalculation>> {
        return repository.loadSavedTipCalculations()
    }
}