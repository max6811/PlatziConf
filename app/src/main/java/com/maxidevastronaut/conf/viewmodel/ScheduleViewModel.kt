package com.maxidevastronaut.conf.viewmodel


import androidx.lifecycle.MutableLiveData
import com.maxidevastronaut.conf.model.Conference
import com.maxidevastronaut.conf.network.Callback
import com.maxidevastronaut.conf.network.FirestoreService
import java.lang.Exception


//encarga de comunicar con nuestra activity
class ScheduleViewModel {
    val firestoreService = FirestoreService()
    var listSchedule: MutableLiveData<List<Conference>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()


    fun refresh (){
        getScheduleFromFirebase()
    }

    fun getScheduleFromFirebase(){
        firestoreService.getSchedule(object: Callback<List<Conference>> {
            override fun onSuccess(result: List<Conference>?) {
                listSchedule.postValue(result)
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