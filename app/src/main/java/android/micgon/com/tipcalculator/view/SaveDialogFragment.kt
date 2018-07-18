package android.micgon.com.tipcalculator.view

import android.app.Dialog
import android.content.Context
import android.micgon.com.tipcalculator.R
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.EditText

class SaveDialogFragment : DialogFragment() {

    interface Callback {
        fun onSaveTip(name: String)
    }

    private var saveTipCallback: SaveDialogFragment.Callback? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        // Did activity implement this interface
        saveTipCallback = context as? Callback
    }

    override fun onDetach() {
        super.onDetach()
        saveTipCallback = null
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val saveDialog = context?.let { ctx ->
            val editText = EditText(ctx)
            editText.id = viewId
            editText.hint = "Enter Location"

            AlertDialog.Builder(ctx)
                    .setView(editText)
                    .setNegativeButton(R.string.action_cancel, null)
                    .setPositiveButton(R.string.action_save, null)
                    .create()
        }

        return saveDialog!!
    }

    private fun onSave(editText: EditText) {
        val text = editText.text.toString()
        if(text.isNotEmpty()) {
            saveTipCallback?.onSaveTip(text)
        }
    }

    companion object {
        val viewId = View.generateViewId()
    }
}