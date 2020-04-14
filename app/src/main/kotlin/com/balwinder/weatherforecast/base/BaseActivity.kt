package com.balwinder.weatherforecast.base

import android.app.ProgressDialog
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.weather.core.utility.KeyBoardUtils
import com.weather.core.utility.extensions.isConnected
import com.weather.core.utility.extensions.showLoadingDialog
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity<VDB : ViewDataBinding, BVM : BaseViewModel<*>> :
    DaggerAppCompatActivity() {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var injectedViewModel: BVM

    private lateinit var viewDataBinding: VDB

    abstract val viewModel: Class<BVM>

    abstract fun getBindingVariable(): Int

    @get:LayoutRes
    abstract val layoutId: Int

    private var mProgressDialog: ProgressDialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()
        initUserInterface()

    }

    override fun onBackPressed() {
        currentFocus?.let {
            KeyBoardUtils.hideKeyboard(it)
        }
        super.onBackPressed()
    }

    protected abstract fun initUserInterface()


    private fun performDataBinding() {

        viewDataBinding = DataBindingUtil.setContentView(this, layoutId)

        injectedViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(viewModel)

        viewDataBinding.setVariable(getBindingVariable(), injectedViewModel)

        viewDataBinding.executePendingBindings()

    }


    fun setProgressMessage(message: String) {
        if (mProgressDialog != null && mProgressDialog!!.isShowing)
            mProgressDialog!!.setMessage(message)
    }


    fun showLoading(message: String) {
        hideLoading()
        mProgressDialog = this.showLoadingDialog(message)
    }

    fun hideLoading() {
        if (mProgressDialog != null && mProgressDialog!!.isShowing) {
            mProgressDialog!!.dismiss()
        }
    }

    fun isConnected() = this.applicationContext.isConnected()


}