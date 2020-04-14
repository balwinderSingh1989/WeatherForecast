package com.balwinder.weatherforecast.ui.main

import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.NonNull
import androidx.navigation.findNavController
import com.balwinder.weatherforecast.BR
import com.balwinder.weatherforecast.R
import com.balwinder.weatherforecast.base.BaseActivity
import com.balwinder.weatherforecast.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity<ActivityMainBinding, MainActivityViewModel>(),
    MainNavigator {

    override val viewModel = MainActivityViewModel::class.java
    override val layoutId = R.layout.activity_main
    override fun getBindingVariable() = BR.viewModel

    override fun initUserInterface() {
        setUpBottomNavigationView()

    }

    private fun setUpBottomNavigationView() {
        val navigationItemSelectedListener =
            object : BottomNavigationView.OnNavigationItemSelectedListener {
                override fun onNavigationItemSelected(@NonNull item: MenuItem): Boolean {
                    when (item.itemId) {
                        R.id.navigation_cities -> {

                            launchCurrentWeather()

                            return true
                        }
                        R.id.navigation_location -> {
                            launchForecastFragment()
                            return true
                        }

                    }
                    return false
                }
            }

        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectedViewModel.setNavigator(this)

    }

    private fun launchCurrentWeather() {
        val navController = findNavController(R.id.host_fragment)
        val graph = navController.navInflater.inflate(R.navigation.main_navigation)
        //if admin user\
        graph.startDestination = R.id.currentWeatherFragment
        navController.graph = graph
    }

    private fun launchForecastFragment() {
        val navController = findNavController(R.id.host_fragment)
        val graph = navController.navInflater.inflate(R.navigation.main_navigation)
        //if admin user\
        graph.startDestination = R.id.forecastFragment
        navController.graph = graph
    }
}
