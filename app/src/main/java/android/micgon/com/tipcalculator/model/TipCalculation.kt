package android.micgon.com.tipcalculator.model

// data class includes baked in functions: equals, hashcode, toString, copy constructor.
data class TipCalculation(
        val locationName: String = "",
        val checkAmount: Double = 0.0,
        val tipPct: Int = 0,
        val tipAmount: Double = 0.0,
        val grandTotal: Double = 0.0
)