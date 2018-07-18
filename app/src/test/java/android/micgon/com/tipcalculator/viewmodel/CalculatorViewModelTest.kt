
package com.acme.tipcalculator.viewmodel

import android.app.Application
import android.micgon.com.tipcalculator.R
import android.micgon.com.tipcalculator.model.Calculator
import android.micgon.com.tipcalculator.model.TipCalculation
import android.micgon.com.tipcalculator.viewmodel.CalculatorViewModel
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyDouble
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class CalculatorViewModelTest {

    lateinit var calculatorViewModel: CalculatorViewModel

    @Mock
    lateinit var mockCalculator: Calculator

    @Mock
    lateinit var mockApplication: Application

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        stubResource(0.0, "$0.00")
        calculatorViewModel = CalculatorViewModel(mockApplication, mockCalculator)
    }

    private fun stubResource(given: Double, returnStub: String) {
        `when`(mockApplication.getString(R.string.dollar_amount, given)).thenReturn(returnStub)
    }

    @Test
    fun testCalculateTip() {
        calculatorViewModel.inputCheckAmount = "10.00"
        calculatorViewModel.inputTipPercentage = "15"

        val stub = TipCalculation(checkAmount = 10.00, tipAmount = 1.5, grandTotal = 11.5)
        `when`(mockCalculator.calculateTip(10.00, 15)).thenReturn(stub)
        stubResource(10.0, "$10.00")
        stubResource(1.5, "$1.50")
        stubResource(11.5, "$11.50")


        calculatorViewModel.calculateTip()

        assertEquals("$10.00", calculatorViewModel.outputCheckAmount)
        assertEquals("$1.50", calculatorViewModel.outputTipAmount)
        assertEquals("$11.50", calculatorViewModel.outputTotalDollarAmount)


    }

    @Test
    fun testCalculateTipBadTipPercent() {

        calculatorViewModel.inputCheckAmount = "10.00"
        calculatorViewModel.inputTipPercentage = ""

        calculatorViewModel.calculateTip()

        verify(mockCalculator, never()).calculateTip(anyDouble(), anyInt())

    }
}
