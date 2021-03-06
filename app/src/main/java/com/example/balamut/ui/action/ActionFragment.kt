package com.example.balamut.ui.action

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.balamut.R

class ActionFragment : Fragment() {

    private lateinit var actionViewModel: ActionViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        actionViewModel =
            ViewModelProvider(this).get(ActionViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_action, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)
        actionViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}