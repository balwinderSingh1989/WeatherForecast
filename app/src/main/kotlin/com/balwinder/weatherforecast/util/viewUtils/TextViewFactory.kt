package com.balwinder.weatherforecast.util.viewUtils

import android.content.Context
import android.graphics.Typeface
import android.os.Build
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.ViewSwitcher
import androidx.annotation.StyleRes

class TextViewFactory(
    private val context: Context, @param:StyleRes @field:StyleRes
    private val styleId: Int, private val center: Boolean, private val typeface: Typeface
) : ViewSwitcher.ViewFactory {

    override fun makeView(): View {
        val textView = TextView(context)
        if (center) {
            textView.gravity = Gravity.CENTER
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            textView.setTextAppearance(context, styleId)
        } else {
            textView.setTextAppearance(styleId)
        }
        textView.typeface = typeface
        return textView
    }

}