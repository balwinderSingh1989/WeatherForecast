package com.balwinder.weatherforecast.util

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import com.balwinder.weatherforecast.R


object DialogUtils {

    fun twoButtonDialog(
        context: Context,
        title: String = "",
        message: String,
        alertDialogListener: AlertDialogListener,
        positiveButtonText: String = context.getString(R.string.dialog_ok),
        negativeButtonText: String = context.getString(R.string.dialog_cancel),
        cancelable: Boolean
    ) {
        val alertDialog = AlertDialog.Builder(context, R.style.AlertDialogTheme)
            .setTitle(title.trim())
            .setMessage(message)
            .setCancelable(cancelable)
            .setPositiveButton(positiveButtonText) { _, _ ->

                alertDialogListener.onPositive()
            }

            .setNegativeButton(negativeButtonText) { _, _ ->
                alertDialogListener.onNegative()
            }
            .create()
        alertDialog.show()
        alertDialog.getButton(DialogInterface.BUTTON_POSITIVE)
            .isAllCaps = false
        alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE)
            .isAllCaps = false
    }

    fun showInfoDialog(
        context: Context,
        title: String = "",
        message: String,
        cancelable: Boolean = true,
        alertDialogListener: AlertDialogListener = object :
            AlertDialogListener {
            override fun onNegative() {
            }

            override fun onPositive() {
            }

        },
        buttonName: Int = R.string.dialog_ok

    ) {
        AlertDialog.Builder(context, R.style.AlertDialogTheme)
            .setTitle(title.trim())
            .setMessage(message)
            .setCancelable(cancelable)
            .setPositiveButton(buttonName) { _, _ ->

                alertDialogListener.onPositive()
            }
            .show()
    }


}