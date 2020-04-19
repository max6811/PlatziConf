package com.maxidevastronaut.conf.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.maxidevastronaut.conf.R

/**
 * A simple [Fragment] subclass.
 */
class SpekersDetailDialogFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_spekers_detail_dialog, container, false)
    }

}
