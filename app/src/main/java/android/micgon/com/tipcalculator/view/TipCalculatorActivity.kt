package android.micgon.com.tipcalculator.view

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.micgon.com.tipcalculator.R
import android.micgon.com.tipcalculator.databinding.ActivityTipCalculatorBinding
import android.micgon.com.tipcalculator.viewmodel.CalculatorViewModel
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

/*  MVVM: Activities, Fragments and Recycler view parts of the view component
    There main responsibility is to bind the view to the view model
*/


class TipCalculatorActivity : AppCompatActivity(), SaveDialogFragment.Callback {

    private lateinit var binding: ActivityTipCalculatorBinding

    override fun onSaveTip(name: String) {
        Snackbar.make(binding.root, "saved $name", Snackbar.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tip_calculator)
        setSupportActionBar(binding.toolbar)

        // creates a new instance of the view model when the app is first launched -
        // then returns same instance when screen is rotated.
        binding.vm = ViewModelProviders.of(this).get(CalculatorViewModel::class.java)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_tip_calculator, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId) {
            R.id.action_save -> {
                showSaveDialog()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showSaveDialog() {
        val saveFragment = SaveDialogFragment()
        saveFragment.show(supportFragmentManager, "saveDialog")
    }
}
