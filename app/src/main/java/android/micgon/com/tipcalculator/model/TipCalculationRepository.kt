package android.micgon.com.tipcalculator.model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData

class TipCalculationRepository {

    // Saving data in memory only
    private val saveTips = mutableMapOf<String, TipCalculation>()

    fun saveTipCalculation(tc: TipCalculation) {
        saveTips[tc.locationName] = tc
    }

    fun loadTipCalculationByName(locationName: String) : TipCalculation? {
        return saveTips[locationName]
    }

    fun loadSavedTipCalculations() : LiveData<List<TipCalculation>> {
        val liveData = MutableLiveData<List<TipCalculation>>()
        liveData.value = saveTips.values.toList()

        return liveData

    }
}