package com.maxidevastronaut.conf.network

import java.lang.Exception

interface Callback<T>{
    fun onSuccess(result: T?) //si tiene exito y no sabe que tipo de val retorna "?"
    fun onFailed(exception: Exception) //caso obtenga error
}