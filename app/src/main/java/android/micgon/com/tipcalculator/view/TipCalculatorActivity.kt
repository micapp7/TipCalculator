package android.micgon.com.tipcalculator.view

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.micgon.com.tipcalculator.R
import android.micgon.com.tipcalculator.databinding.ActivityTipCalculatorBinding
import android.micgon.com.tipcalculator.viewmodel.CalculatorViewModel
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/*  MVVM: Activities, Fragments and Recycler view parts of the view component
    There main responsibility is to bind the view to the view model
*/


class TipCalculatorActivity : AppCompatActivity() {

    lateinit var binding: ActivityTipCalculatorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tip_calculator)
        setSupportActionBar(binding.toolbar)

        // creates a new instance of the view model when the app is first launched -
        // then returns same instance when screen is rotated.
        binding.vm = ViewModelProviders.of(this).get(CalculatorViewModel::class.java)




    }
}
