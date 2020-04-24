package com.maxidevastronaut.conf.view.adapter

import com.maxidevastronaut.conf.model.Speaker

interface SpeakerListener{
    fun onSpeakerClicked(speaker: Speaker, position: Int)
}