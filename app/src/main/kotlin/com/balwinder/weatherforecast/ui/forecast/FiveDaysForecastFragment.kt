package com.balwinder.weatherforecast.ui.forecast

import android.Manifest
import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import com.balwinder.weatherforecast.BR
import com.balwinder.weatherforecast.R
import com.balwinder.weatherforecast.base.BaseFragment
import com.balwinder.weatherforecast.databinding.ForecastFragmentBinding
import com.balwinder.weatherforecast.ui.currentweatherfragment.CurrentWeatherNavigator
import com.balwinder.weatherforecast.ui.currentweatherfragment.CurrentWeatherViewModel
import com.balwinder.weatherforecast.ui.forecast.adapter.FiveDaysForecastWeatherAdapter
import com.balwinder.weatherforecast.util.AlertDialogListener
import com.balwinder.weatherforecast.util.DialogUtils
import com.balwinder.weatherforecast.util.permission.askPermission
import com.balwinder.weatherforecast.util.setWeatherIcon
import com.balwinder.weatherforecast.util.viewUtils.TextViewFactory
import com.weather.core.data.repository.currentweather.model.CurrentWeatherResponse
import com.weather.core.data.repository.forecastWeather.model.ItemHourly
import com.weather.core.domain.remote.exception.PermissionException
import com.weather.core.utility.constants.getWeatherStatus
import kotlinx.android.synthetic.main.forecast_fragment.*
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import javax.inject.Named


class FiveDaysForecastFragment :
    BaseFragment<ForecastFragmentBinding, FiveDayForecastViewModel>(),
    ForecastNavigator, CurrentWeatherNavigator {

    override val bindingVariable = BR.viewModel

    override val viewModel = FiveDayForecastViewModel::class.java

    override fun getLayoutId() = R.layout.forecast_fragment

    lateinit var fiveDaysForecastWeatherAdapter: FiveDaysForecastWeatherAdapter


    lateinit var currentWeatherViewModel: CurrentWeatherViewModel
    @Inject
    @Named("CurrentWeatherViewModel")
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    var fiveDaysWeatherList: ArrayList<ArrayList<ItemHourly>> = arrayListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        injectedViewModel.setNavigator(this)

        lifecycle.addObserver(injectedViewModel)
        currentWeatherViewModel = ViewModelProviders.of(
            activity!!,
            mViewModelFactory
        ).get(CurrentWeatherViewModel::class.java)

        currentWeatherViewModel.setNavigator(this)


    }

    private fun initForecastAdapter() {
        fiveDaysForecastWeatherAdapter = FiveDaysForecastWeatherAdapter(null, context!!)
        { item, cardView, forecastIcon, dayOfWeek, temp, tempMaxMin ->
            val action =
                FiveDaysForecastFragmentDirections.actionForecastFragmentToHourlyForecastFragment(
                    item.toTypedArray()

                )
            findNavController()
                .navigate(
                    action,
                    FragmentNavigator.Extras.Builder()
                        .addSharedElements(
                            mapOf(
                                cardView to cardView.transitionName,
                                forecastIcon to forecastIcon.transitionName,
                                dayOfWeek to dayOfWeek.transitionName,
                                temp to temp.transitionName,
                                tempMaxMin to tempMaxMin.transitionName
                            )
                        )
                        .build()
                )
        }


        rvDayForecast.adapter = fiveDaysForecastWeatherAdapter
        postponeEnterTransition()
        rvDayForecast.viewTreeObserver
            .addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }

    }


    override fun initUserInterface(rootView: View) {

        setupTextSwitchers(context!!)

        initForecastAdapter()
        initSwipeRefreshLayout()

        injectedViewModel.forecastWeatherLiveData.observe(viewLifecycleOwner, Observer {
            swipeRefreshLayout.isRefreshing = false

            fiveDaysForecastWeatherAdapter.setItems(it as ArrayList<ArrayList<ItemHourly>>)
            fiveDaysWeatherList = it

        }
        )
        currentWeatherViewModel.currentWeatherLiveData.observe(viewLifecycleOwner, Observer {
            swipeRefreshLayout.isRefreshing = false
            populateCurrentWeather(it[0], context!!)
        }
        )
        /**
         * observe location
         * Fetch current and forecast for 5 days / 3 hours  based on location
         */
        injectedViewModel.locaitonLiveData.observe(viewLifecycleOwner, Observer {

            loadWeatherForecast(it!!.latitude.toString(), it.longitude.toString())

            currentWeatherViewModel.loadCurrenLocationtWeather(
                it.latitude.toString(),
                it.longitude.toString()
            )

        })

        checkLocationPermission()


    }


    private fun initSwipeRefreshLayout() {
        swipeRefreshLayout.setColorSchemeResources(
            android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light
        )
        swipeRefreshLayout.setOnRefreshListener {
            injectedViewModel.getLastLocation()
        }

    }

    private fun loadWeatherForecast(lat: String, long: String) {
        swipeRefreshLayout.isRefreshing = false
        injectedViewModel.loadFiveDayForecast(lat, long)
    }


    private fun populateCurrentWeather(item: CurrentWeatherResponse, context: Context) {
        temp_text_view.setText(
            String.format(
                Locale.getDefault(),
                "%.0f°", item.main!!.temp
            )
        )
        wind_text_view.setText(
            String.format(
                Locale.getDefault(),
                context.resources.getString(R.string.wind_unit_label),
                item.wind!!.speed
            )
        )
        humidity_text_view.setText(
            String.format(
                Locale.getDefault(),
                "%d%%",
                item.main!!.humidity
            )
        )

        description_text_view.setText(
            getWeatherStatus(
                item.weather!![0].id
            )
        )

        setWeatherIcon(
            context, imageView,
            item.weather!![0].id
        )

    }


    override fun showAlert(stringId: Int, message: String) {
        swipeRefreshLayout.isRefreshing= false
        DialogUtils.showInfoDialog(context!!, "Error", message)
    }


    private fun setupTextSwitchers(context: Context) {
        var typeface = Typeface.createFromAsset(context.assets, "fonts/Vazir.ttf")

        temp_text_view.setFactory(
            TextViewFactory(
                context,
                R.style.TempTextView,
                true,
                typeface
            )
        )
        temp_text_view.setInAnimation(context, R.anim.slide_in_right)
        temp_text_view.setOutAnimation(context, R.anim.slide_out_left)
        description_text_view.setFactory(
            TextViewFactory(
                context,
                R.style.DescriptionTextView,
                true,
                typeface
            )
        )


        description_text_view.setInAnimation(context, R.anim.slide_in_right)
        description_text_view.setOutAnimation(context, R.anim.slide_out_left)
        humidity_text_view.setFactory(
            TextViewFactory(
                context,
                R.style.HumidityTextView,
                false,
                typeface
            )
        )
        humidity_text_view.setInAnimation(context, R.anim.slide_in_bottom)
        humidity_text_view.setOutAnimation(context, R.anim.slide_out_top)
        wind_text_view.setFactory(
            TextViewFactory(
                context,
                R.style.WindSpeedTextView,
                false,
                typeface
            )
        )
        wind_text_view.setInAnimation(context, R.anim.slide_in_bottom)
        wind_text_view.setOutAnimation(context, R.anim.slide_out_top)
    }

    private fun checkLocationPermission() {
        GlobalScope.launch(
            Dispatchers.Main,
            CoroutineStart.DEFAULT
        ) {
            try {
                askPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                injectedViewModel.getLastLocation()

            } catch (e: PermissionException) {
                if (e.hasForeverDenied()) {
                    showPermissionFromSettingsDialog(e)
                }
            }
        }
    }

    private fun showPermissionFromSettingsDialog(e: PermissionException) {
        DialogUtils.twoButtonDialog(
            context!!,
            "",
            getString(R.string.locaiton_permission),
            object : AlertDialogListener {
                override fun onPositive() {
                    e.goToSettings()
                }

                override fun onNegative() {
                }

            }, getString(R.string.settings), cancelable = true
        )
    }


}