package android.micgon.com.tipcalculator.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.databinding.Observable
import android.databinding.PropertyChangeRegistry
import android.micgon.com.tipcalculator.BR

// Get benefit of ViewModel (configuration change handling) component and data binding observable

abstract class ObservableViewModel(app: Application) : AndroidViewModel(app), Observable {

    @delegate:Transient
    private val callbacks: PropertyChangeRegistry by lazy { PropertyChangeRegistry() }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.add(callback)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.remove(callback)
    }

    fun notifyChange() {
        callbacks.notifyChange(this, BR._all)
    }
}