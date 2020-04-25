package com.maxidevastronaut.conf.view.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.maxidevastronaut.conf.R
import com.maxidevastronaut.conf.model.Speaker
import com.maxidevastronaut.conf.view.adapter.SpeakerAdapter
import com.maxidevastronaut.conf.view.adapter.SpeakerListener
import com.maxidevastronaut.conf.viewmodel.SpeakerViewModel
import kotlinx.android.synthetic.main.fragment_speakers.*

/**
 * A simple [Fragment] subclass.
 */
class SpeakersFragment : Fragment(), SpeakerListener {

    private lateinit var speakerAdapter: SpeakerAdapter
    private lateinit var viewModel: SpeakerViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_speakers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SpeakerViewModel::class.java)
        viewModel.refresh()
        speakerAdapter = SpeakerAdapter(this)
        rvSpeaker.apply {
            //layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
            layoutManager =GridLayoutManager(context, 2)//esto para que muestre en dos sections
            adapter = speakerAdapter
        }
        observeViewModel()
    }
    fun observeViewModel(){
        viewModel.listSpeaker.observe(this.viewLifecycleOwner, Observer<List<Speaker>>{ speaker ->
            speakerAdapter.updateData(speaker)
        })
        viewModel.isLoading.observe(this.viewLifecycleOwner, Observer<Boolean>{
            if (it != null){
                rlBaseSpeaker.visibility = View.INVISIBLE
            }
        })
    }

    override fun onSpeakerClicked(speaker: Speaker, position: Int) {
        var bundle = bundleOf("speaher" to speaker)
        findNavController().navigate(R.id.speakerDetailFragmentDialog, bundle)
    }

}
