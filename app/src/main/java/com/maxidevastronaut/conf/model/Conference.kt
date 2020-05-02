package com.maxidevastronaut.conf.model

import android.accounts.AuthenticatorDescription
import java.io.Serializable
import java.util.*

class Conference: Serializable {
    //inicializacion Tardia sin valor de inicio
    lateinit var  title: String
    lateinit var description: String
    lateinit var tag: String
    lateinit var datetime: Date
    lateinit var speaker: String

}