package com.weather.core.utility

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

object KeyBoardUtils {

    fun showKeyboard(view: View) {
        getInputMethodManager(view.context)
            .showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
    }

    fun hideKeyboard(view: View) {
        getInputMethodManager(view.context)
            .hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun isKeyboardShowing(context: Context) =
        getInputMethodManager(context).isAcceptingText

    private fun getInputMethodManager(context: Context) =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

}