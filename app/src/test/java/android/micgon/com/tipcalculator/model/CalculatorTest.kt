package android.micgon.com.tipcalculator.model


import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CalculatorTest {

    lateinit var calculator: Calculator

    @Before
    fun setup() {
        calculator = Calculator()
    }

    @Test
    fun testCalculateTip() {

        // Base case for test values below
        val baseTc = TipCalculation(checkAmount = 10.00)

        // data class function - allows copy function: use base case value for a list of test
        val testVals = listOf(baseTc.copy(tipPct = 25, tipAmount = 2.5, grandTotal = 12.5),
                baseTc.copy(tipPct = 15, tipAmount = 1.5, grandTotal = 11.5),
                baseTc.copy(tipPct = 18, tipAmount = 1.8, grandTotal = 11.8))

        testVals.forEach {
            assertEquals(it,
                    calculator.calculateTip(it.checkAmount, it.tipPct))
        }
    }
}