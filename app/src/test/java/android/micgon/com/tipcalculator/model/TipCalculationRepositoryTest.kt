package android.micgon.com.tipcalculator.model

import android.arch.core.executor.testing.InstantTaskExecutorRule
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class TipCalculationRepositoryTest {
    lateinit var repository: TipCalculationRepository

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()


    @Before
    fun setup() {
        repository = TipCalculationRepository()
    }

    @Test
    fun testSaveTip() {
        val tip = TipCalculation(locationName = "In And Out Burger",
                checkAmount = 100.0, tipPct = 25,
                tipAmount = 25.0, grandTotal = 125.0)

        repository.saveTipCalculation(tip)

        assertEquals(tip, repository.loadTipCalculationByName(tip.locationName))
    }

    @Test
    fun testLoadTipCalculations() {
        val tip1 = TipCalculation(locationName = "In And Out Burger",
                checkAmount = 100.0, tipPct = 25,
                tipAmount = 25.0, grandTotal = 125.0)

        val tip2 = TipCalculation(locationName = "Captain Georges",
                checkAmount = 50.0, tipPct = 25,
                tipAmount = 25.0, grandTotal = 125.0)

        repository.saveTipCalculation(tip1)
        repository.saveTipCalculation(tip2)

        repository.loadSavedTipCalculations().observeForever { tipCalculations ->
            assertEquals(2, tipCalculations?.size)

        }

    }

}

