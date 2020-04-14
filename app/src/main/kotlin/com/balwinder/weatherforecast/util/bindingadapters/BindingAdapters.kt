package com.balwinder.weatherforecast.util.bindingadapters

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter

class BindingAdapters {

    companion object {

        @BindingAdapter("app:srcCompat")
        @JvmStatic
        fun setImageViewResource(
            appCompatImageView: AppCompatImageView,
            resourceId: Int
        ) {
            appCompatImageView.setImageResource(resourceId)
        }

        @BindingAdapter("android:src")
        @JvmStatic
        fun setImageViewResource(imageView: ImageView, resourceId: Int) {
            imageView.setImageResource(resourceId)
        }

        @BindingAdapter("android:src")
        @JvmStatic
        fun setImageViewBitmap(imageView: ImageView, bitmap: Bitmap?) {
            if (bitmap != null)
                imageView.setImageBitmap(bitmap)
        }

        @BindingAdapter("android:src")
        @JvmStatic
        fun setImageViewDrawable(imageView: ImageView, drawable: Drawable) {
            imageView.setImageDrawable(drawable)
        }

        @BindingAdapter("android:text")
        @JvmStatic
        fun setTextViewStringResource(
            appCompatTextView: AppCompatTextView,
            resourceId: Int
        ) {
            if (resourceId != 0) {
                appCompatTextView.text =
                    appCompatTextView.resources.getString(resourceId)
            }
        }


        @BindingAdapter("android:background")
        @JvmStatic
        fun setConstraintLayoutBackgroundDrawableResource(
            constraintLayout: ConstraintLayout,
            @DrawableRes resourceId: Int
        ) {
            if (resourceId != 0) {
                constraintLayout.background =
                    ContextCompat.getDrawable(
                        constraintLayout.context,
                        resourceId
                    )
            }
        }


        @JvmStatic
        @BindingAdapter("setWebViewClient")
        fun setWebViewClient(webView: WebView, webViewClient: WebViewClient) {
            webView.webViewClient = webViewClient
        }

        @JvmStatic
        @BindingAdapter("loadUrl")
        fun loadUrl(view: WebView, url: String) {
            view.loadUrl(url)
        }
    }
}