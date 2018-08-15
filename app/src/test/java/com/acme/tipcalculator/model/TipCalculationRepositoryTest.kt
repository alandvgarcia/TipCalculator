package com.acme.tipcalculator.model

import android.arch.core.executor.testing.InstantTaskExecutorRule
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class TipCalculationRepositoryTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    lateinit var repository: TipCalculationRepository

    @Before
    fun setup() {
        repository = TipCalculationRepository()
    }

    @Test
    fun testeSaveTip() {
        val tip = TipCalculation(locationName = "Pancake Paradise",
                checkAmount = 100.0, tipPct = 25,
                tipAmount = 25.0, grandTotal = 125.0)

        repository.saveCalculation(tip)

        assertEquals(tip, repository.loadTipCalculationByName(tip.locationName))
    }

    @Test
    fun testeLoadSavedTipCalculations() {
        val tip1 = TipCalculation(locationName = "Pancake Paradise",
                checkAmount = 100.0, tipPct = 25,
                tipAmount = 25.0, grandTotal = 125.0)

        val tip2 = TipCalculation(locationName = "Veggie Sensation",
                checkAmount = 100.0, tipPct = 25,
                tipAmount = 25.0, grandTotal = 125.0)


        repository.saveCalculation(tip1)
        repository.saveCalculation(tip2)

        repository.loadSavedTipCalculations().observeForever { tipCalculations ->
            assertEquals(2, tipCalculations?.size)
        }

    }

}