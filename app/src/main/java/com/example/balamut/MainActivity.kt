package com.example.balamut

import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController

class MainActivity : AppCompatActivity() {
    val navView: BottomNavigationView by lazy { findViewById(R.id.nav_view) }
    val navController: NavController by lazy { findNavController(R.id.nav_host_fragment) }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navView.visibility = View.GONE

    }
    fun toMainGraph() {
        navController.navigate(
                R.id.action_authorizationFragment2_to_navigation)
        navView.visibility = View.VISIBLE

        val appBarConfiguration = AppBarConfiguration(
                setOf(
                        R.id.navigation_progress, R.id.navigation_action, R.id.navigation_profile
                )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

    }
    override fun onBackPressed() {
        super.onBackPressed()
    }
    override fun onSupportNavigateUp() = findNavController(R.id.nav_host_fragment).navigateUp()
}