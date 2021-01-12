package com.example.balamut

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

class AuthorizationFragment : Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_authorization, container, false)


        val but_login_number : Button = view.findViewById(R.id.go_in_with_number)
        but_login_number.setOnClickListener{
            (requireActivity() as MainActivity).toMainGraph()

        }
        val but_login_google : Button = view.findViewById(R.id.go_in_with_google)
        but_login_google.setOnClickListener {
            view.findNavController().navigate(R.id.action_authorizationFragment2_to_registGoogleFragment)
        }

        return view

    }
}