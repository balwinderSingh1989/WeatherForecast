package com.balwinder.weatherforecast.base

import androidx.lifecycle.ViewModel
import com.weather.core.data.cache.DataManager
import com.weather.core.domain.resourceProvider.ResourceProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.lang.ref.WeakReference
import javax.inject.Inject


abstract class BaseViewModel<N> : ViewModel() {

    @Inject
    lateinit var resourceProvider: ResourceProvider


    @Inject
    lateinit var dataManager: DataManager

    lateinit var navigator: WeakReference<N>

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {

        with(compositeDisposable) {
            if (!isDisposed) {
                dispose()
                compositeDisposable = CompositeDisposable()
            }
        }

        super.onCleared()
    }

    fun getNavigator(): N? {
        return navigator.get()
    }

    fun setNavigator(navigator: N) {
        this.navigator = WeakReference(navigator)
    }

    fun addDisposable(disposable: Disposable?) {

        disposable?.let {
            if (it.isDisposed.not()) {
                compositeDisposable.add(disposable)
            }
        }

    }

    @JvmName("getResourceProvider_")
    fun getResourceProvider(): ResourceProvider {
        return this.resourceProvider
    }


    @JvmName("getDataManager_")
    fun getDataManager(): DataManager {
        return this.dataManager
    }


}