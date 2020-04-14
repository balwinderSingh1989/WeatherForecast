package com.weather.core.utility.extensions

import android.annotation.TargetApi
import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.widget.Toast
import com.weather.core.utility.constants.isOnMainThread


fun Context.getDeviceId() = Settings.Secure.getString(
    this.contentResolver, Settings.Secure.ANDROID_ID
)


fun Context.showErrorToast(msg: String, length: Int = Toast.LENGTH_LONG) {
  //   toast(String.format(getString(R.string.an_error_occurred), msg), length)
     toast( msg, length)
}


fun Context.showErrorToast(exception: Exception, length: Int = Toast.LENGTH_LONG) {
    showErrorToast(exception.toString(), length)
}


fun Context.toast(id: Int, length: Int = Toast.LENGTH_SHORT) {
    toast(getString(id), length)
}

fun Context.toast(msg: String, length: Int = Toast.LENGTH_SHORT) {
    try {
        if (isOnMainThread()) {
            doToast(this, msg, length)
        } else {
            Handler(Looper.getMainLooper()).post {
                doToast(this, msg, length)
            }
        }
    } catch (e: Exception) {
    }
}

@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
private fun doToast(context: Context, message: String, length: Int) {
    if (context is Activity) {
        if (!context.isFinishing && !context.isDestroyed) {
            Toast.makeText(context, message, length).show()
        }
    } else {
        Toast.makeText(context, message, length).show()
    }
}

fun Context.isConnected(): Boolean {
    val connectivityManager =
        this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    if (connectivityManager != null) {
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
    return false

}


fun Context.showLoadingDialog(message: String): ProgressDialog {
    val progressDialog = ProgressDialog(this)
    progressDialog.show()
    progressDialog.setMessage(message)
    progressDialog.isIndeterminate = true
    progressDialog.setCancelable(false)
    progressDialog.setCanceledOnTouchOutside(false)
    return progressDialog
}








