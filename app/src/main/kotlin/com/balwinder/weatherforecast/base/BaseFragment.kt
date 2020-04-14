package com.balwinder.weatherforecast.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.ActionBar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.balwinder.weatherforecast.util.DialogUtils
import com.weather.core.utility.extensions.isConnected
import com.weather.core.utility.extensions.toast
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment<VDB : ViewDataBinding
        , BVM : BaseViewModel<*>> : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewDataBinding: VDB

    lateinit var injectedViewModel: BVM

    abstract val bindingVariable: Int

    abstract val viewModel: Class<BVM>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        injectedViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(viewModel)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewDataBinding = DataBindingUtil.inflate(
            inflater,
            getLayoutId(),
            container,
            false
        )

        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewDataBinding.setVariable(
            bindingVariable,
            injectedViewModel
        )
        initUserInterface(view)
        viewDataBinding.executePendingBindings()


    }

    protected abstract fun getLayoutId(): Int

    protected abstract fun initUserInterface(rootView: View)


    fun getSupportFragmentActionBar(): ActionBar? {
        val activity = (activity as BaseActivity<*, *>)
        return activity.supportActionBar

    }


    protected fun isAlive(): Boolean {
        return !(isRemoving ||
                activity == null ||
                isDetached ||
                isAdded ||
                view == null)
    }

    fun getFragmentTag(): String = javaClass.simpleName

    open fun onBackPressed() {}

    fun isConnected(): Boolean {
        return this.context!!.isConnected()
    }


    fun showToast(message: String) {
        activity!!.toast(message)
    }


    fun setProgressMessage(message: String) {
        (activity as BaseActivity<*, *>).setProgressMessage(message)
    }


    fun showLoading(message: String) {
        (activity as BaseActivity<*, *>).showLoading(message)
    }


    fun hideLoading() {
        (activity as BaseActivity<*, *>).hideLoading()
    }


    private var onBackPressedCallback =
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                onBackPressed()
            }
        }


    /**
     * Enables OnBackPressedCallback event
     */
    open fun enableOnBackPressedCallback() {
        requireActivity()
            .onBackPressedDispatcher
            .addCallback(
                this,
                onBackPressedCallback
            )
        onBackPressedCallback.isEnabled = true
    }

    /**
     * Disables OnBackPressedCallback event
     */
    fun disableOnBackPressedCallback() {
        onBackPressedCallback.isEnabled = false
    }


    open fun showFragmentDialog(titleId: Int, message: String) {
        DialogUtils
            .showInfoDialog(
                context = activity as BaseActivity<*, *>,
                title = getString(titleId),
                message = message,
                cancelable = false
            )

    }

}