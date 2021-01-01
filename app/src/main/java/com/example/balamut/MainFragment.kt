package com.example.balamut

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.balamut.R.id.action_mainFragment_to_progressFragment
import com.google.android.material.navigation.NavigationView

class MainFragment: Fragment(){
    val navController = requireActivity().findNavController(R.id.nav_host_fragment)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  super.onCreateView(inflater, container, savedInstanceState)



        val but_progress : Button = view!!.findViewById(R.id.item_progress)
        val but_action : Button = view.findViewById(R.id.item_action)
        val but_profile : Button = view.findViewById(R.id.item_profile)


        /*but_progress.setOnClickListener {
            Navigation.findNavController(view).navigate(action_mainFragment_to_progressFragment)
        }
        but_action.setOnClickListener {
            view.findNavController().navigate(R.id.action_mainFragment_to_actionFragment)
        }
        but_profile.setOnClickListener {
            view.findNavController().navigate(R.id.action_mainFragment_to_profileFragment)
        }*/
        return view
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        NavigationUI.onNavDestinationSelected(item, navController)
        return super.onOptionsItemSelected(item)
    }
}