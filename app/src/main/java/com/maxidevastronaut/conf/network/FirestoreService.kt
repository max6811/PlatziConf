package com.maxidevastronaut.conf.network

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.maxidevastronaut.conf.model.Conference
import com.maxidevastronaut.conf.model.Speaker
import com.maxidevastronaut.conf.network.Callback as Callback

const val CONFERENCES_COLLECTION_NAME = "conferences"
const val SPEAKERS_COLLECTION_NAME = "speakers"
class FirestoreService{
    //crear una instancias inicial de firestore
    val firebaseFirestore = FirebaseFirestore.getInstance()//coneccion directa con bd
    val settings = FirebaseFirestoreSettings.Builder().setPersistenceEnabled(true).build()
    //inicializador de kot que se inicia al ejec tu app
    init {
        //los datos se mantin percistentes despues del primer run aunque no tengamos internet
        firebaseFirestore.firestoreSettings = settings
    }
    //funciones
    fun getSpeakers(callback: Callback<List<Speaker>>){
        firebaseFirestore.collection("speakers")
            .orderBy("category")
            .get()
            .addOnSuccessListener { result ->
                for(doc in result){
                    //todo los speakers de la collection fs
                    val list = result.toObjects(Speaker::class.java)
                    callback.onSuccess(list)
                    break
                }
            }
    }

    fun getSchedule(callback: Callback<List<Conference>>){
        firebaseFirestore.collection(CONFERENCES_COLLECTION_NAME)
            .get()
            .addOnSuccessListener { result ->
                for(doc in result){
                    //todo los schedule de la collection fs
                    val list = result.toObjects(Conference::class.java)
                    callback.onSuccess(list)
                    break
                }
            }
    }
}