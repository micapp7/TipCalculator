package android.micgon.com.tipcalculator.viewmodel

import android.app.Application
import android.micgon.com.tipcalculator.R
import android.micgon.com.tipcalculator.model.Calculator
import android.micgon.com.tipcalculator.model.TipCalculation
import android.util.Log


class CalculatorViewModel @JvmOverloads constructor(
        app: Application, val calculator: Calculator = Calculator()) : ObservableViewModel(app) {

    private val TAG = "CalculatorViewModel"

    var inputCheckAmount = ""
    var inputTipPercentage = ""

    var outputCheckAmount = ""
    var outputTipAmount = ""
    var outputTotalDollarAmount = ""

    init {
        updateOutputs(TipCalculation())
    }


    fun calculateTip() {
        Log.d(TAG, "calculateTip Invoked")

        // convert to numbers
        val checkAmount = inputCheckAmount.toDouble()
        val tipPct = inputTipPercentage.toIntOrNull()

        if (checkAmount != null && tipPct != null) {
            Log.d(TAG, "CheckAmount: $checkAmount, TipPercentage: $tipPct")
            updateOutputs(calculator.calculateTip(checkAmount, tipPct))
            notifyChange()

        }
    }

    private fun updateOutputs(tc: TipCalculation) {
        outputCheckAmount = getApplication<Application>().getString(R.string.dollar_amount, tc.checkAmount)
        outputTipAmount = getApplication<Application>().getString(R.string.dollar_amount, tc.tipAmount)
        outputTotalDollarAmount = getApplication<Application>().getString(R.string.dollar_amount, tc.grandTotal)
    }

    fun clearInputs() {
        inputCheckAmount = "0.00"
        inputTipPercentage = "0"

    }
}


