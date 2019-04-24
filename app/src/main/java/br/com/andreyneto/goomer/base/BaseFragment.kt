package br.com.andreyneto.goomer.base

import android.view.View
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import br.com.andreyneto.goomer.R
import br.com.andreyneto.goomer.ui.AppViewModel
import com.google.android.material.snackbar.Snackbar

open class BaseFragment : Fragment() {
    var errorSnackbar: Snackbar? = null
    lateinit var viewModel: AppViewModel

    fun showError(bindingRoot: View, @StringRes errorMessage: Int) {
        errorSnackbar = Snackbar.make(
            bindingRoot,
            errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(
            R.string.retry,
            viewModel.errorClickListener)
        errorSnackbar?.show()
    }

    fun View.snackbar(message: String) {
        Snackbar.make(this, message, Snackbar.LENGTH_LONG).show()
    }

    fun hideError() {
        errorSnackbar?.dismiss()
    }
}