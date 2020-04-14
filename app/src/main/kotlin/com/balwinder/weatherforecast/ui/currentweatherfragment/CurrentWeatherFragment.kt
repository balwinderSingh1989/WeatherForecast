package com.balwinder.weatherforecast.ui.currentweatherfragment

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import com.balwinder.weatherforecast.BR
import com.balwinder.weatherforecast.R
import com.balwinder.weatherforecast.base.BaseFragment
import com.balwinder.weatherforecast.databinding.CurrentWeatherFragmentBinding
import com.balwinder.weatherforecast.ui.currentweatherfragment.adapter.CurrentWeatherAdapter
import com.balwinder.weatherforecast.util.DialogUtils
import com.weather.core.data.repository.currentweather.model.CurrentWeatherResponse
import com.weather.core.utility.KeyBoardUtils
import com.weather.core.utility.logger.AppLogger
import kotlinx.android.synthetic.main.current_weather_fragment.*


class CurrentWeatherFragment :
    BaseFragment<CurrentWeatherFragmentBinding, CurrentWeatherViewModel>(),
    CurrentWeatherNavigator {

    override val bindingVariable = BR.viewModel

    override val viewModel = CurrentWeatherViewModel::class.java

    override fun getLayoutId() = R.layout.current_weather_fragment

    lateinit var currentWeatherAdapter: CurrentWeatherAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectedViewModel.setNavigator(this)

    }

    override fun initUserInterface(rootView: View) {

        initSearchView(rootView)

        observeWeatherListLiveData()

        initWeatherAdapter()


    }

    private fun initWeatherAdapter() {
        currentWeatherAdapter = CurrentWeatherAdapter(null, context!!)

        rvWeather.apply {
            adapter = currentWeatherAdapter
        }
    }

    private fun observeWeatherListLiveData() {
        injectedViewModel.currentWeatherLiveData.observe(viewLifecycleOwner, Observer {
            currentWeatherAdapter.setItems(it as ArrayList<CurrentWeatherResponse>)
        }
        )
    }

    private fun initSearchView(rootView: View) {

        svCities.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(cities: String): Boolean {
                AppLogger.d("search", cities)
                injectedViewModel.loadCurrentWeather(cities)
                KeyBoardUtils.hideKeyboard(rootView)
                svCities.clearFocus()
                currentWeatherAdapter.clearItems()
                return true
            }

            override fun onQueryTextChange(s: String): Boolean {
                return true
            }
        })
        svCities.setOnQueryTextFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                KeyBoardUtils.showKeyboard(view.findFocus())
            }
        }
        svCities.setOnSearchClickListener {
            KeyBoardUtils.showKeyboard(it.findFocus())
        }
    }

    override fun showAlert(stringId: Int, message: String) {
        DialogUtils.showInfoDialog(context!!, "Error", message)
    }
}