package com.maxidevastronaut.conf.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maxidevastronaut.conf.model.Speaker
import com.maxidevastronaut.conf.network.Callback
import com.maxidevastronaut.conf.network.FirestoreService

class SpeakersViewModel : ViewModel(){
    val firestoreService = FirestoreService()
    var listSpeakers : MutableLiveData<List<Speaker>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun refresh() {
        getSpeakersFromFirebase()
    }
    fun getSpeakersFromFirebase(){
        firestoreService.getSpeakers(object: Callback<List<Speaker>>{
            override fun onSuccess(result: List<Speaker>?) {
                listSpeakers.postValue(result)
                processFinished()
            }

            override fun onFailed(exception: Exception) {
                processFinished()
            }
        })
    }
    fun processFinished(){
        isLoading.value = true
    }
}