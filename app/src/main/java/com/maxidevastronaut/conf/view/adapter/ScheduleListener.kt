package com.maxidevastronaut.conf.view.adapter

import com.maxidevastronaut.conf.model.Conference


interface ScheduleListener{
    fun onConferenceClicked(conference: Conference, position: Int)
}