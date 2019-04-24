package br.com.andreyneto.goomer.utils

import android.content.ContextWrapper
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import br.com.andreyneto.goomer.model.Menu
import br.com.andreyneto.goomer.model.Sale
import android.app.Activity
import android.view.inputmethod.InputMethodManager


fun View.getParentActivity(): AppCompatActivity? {
    var context = this.context
    while (context is ContextWrapper) {
        if (context is AppCompatActivity) {
            return context
        }
        context = context.baseContext
    }
    return null
}

fun Activity.hideKeyboard() {
    val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    //Find the currently focused view, so we can grab the correct window token from it.
    var view = currentFocus
    //If no view currently has focus, create a new one, just so we can grab a window token from it
    if (view == null) {
        view = View(this)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}