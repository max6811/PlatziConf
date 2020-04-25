package com.maxidevastronaut.conf.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import com.maxidevastronaut.conf.R
import com.maxidevastronaut.conf.model.Conference
import com.maxidevastronaut.conf.view.adapter.ScheduleAdapter
import com.maxidevastronaut.conf.view.adapter.ScheduleListener
import com.maxidevastronaut.conf.viewmodel.ScheduleViewModel
import kotlinx.android.synthetic.main.fragment_schedule.*

/**
 * A simple [Fragment] subclass.
 * puente mas cercano a nuestra interfaz de usuario
 */

class ScheduleFragment : Fragment(), ScheduleListener {
//forma de implementar el ScheduleListener usando ","
// y despues corregir error e implemnt nueva funcion onConferenceClicked()

    private lateinit var scheduleAdapter: ScheduleAdapter
    private lateinit var viewModel: ScheduleViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_schedule, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ScheduleViewModel::class.java)
        viewModel.refresh()

        scheduleAdapter = ScheduleAdapter(this)
        rvSchedule.apply{
            layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false )
            adapter = scheduleAdapter
        }
        //observar los datos
        observeViewModel()
    }

    fun  observeViewModel(){
        viewModel.listSchedule.observe(this.viewLifecycleOwner, Observer<List<Conference>>{ schedule ->
            scheduleAdapter.updateData(schedule)//enviamos al adaptador
        })
        //CONTROLA CUANDO LOS DATOS TEMINA DE CARGAR
        viewModel.isLoading.observe(this.viewLifecycleOwner, Observer<Boolean>{
            if (it != null){
                rlBaseSchedule.visibility = View.INVISIBLE
            }
        })
    }

    override fun onConferenceClicked(conference: Conference, position: Int) {
        val bundle = bundleOf("conference" to conference)
        findNavController().navigate(R.id.scheduleDetailFragmentDialog, bundle)//vamos al fragmento y enviandole un object
    }

}
