package com.maxidevastronaut.conf.view.ui.fragments

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.*

import com.maxidevastronaut.conf.R
import com.maxidevastronaut.conf.model.Ubication

/**
 * A simple [Fragment] subclass.
 */
class UbicationFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ubication, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }
    //al inicar el mapa se dirige al lugar con una aimacion de zoom
    override fun onMapReady(googleMap: GoogleMap?) {
        val ubication = Ubication()
        val zoom = 16f
        val centerMap = LatLng(ubication.latitud, ubication.longitud)
        googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(centerMap, zoom))

        //para hacer un marcado de un determinado lugar
        val centerMark = LatLng(ubication.latitud, ubication.longitud)
        val markerOptions = MarkerOptions()

        markerOptions.position(centerMap)
        markerOptions.title("Platzi Conf 2019")

        val bitmapDraw = context?.applicationContext?.let { ContextCompat.getDrawable(it, R.drawable.logo_platzi) as BitmapDrawable }
        val smallMarker = Bitmap.createScaledBitmap(bitmapDraw?.bitmap,150,150,false)//dimensiones para el punto
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(smallMarker))
        googleMap?.addMarker(markerOptions)//agregamos al mapa de manera segura "?"

        //para acceder a evento del click
        googleMap?.setOnMarkerClickListener (this)

        // colocar el JSON  de estilos
        googleMap?.setMapStyle(MapStyleOptions.loadRawResourceStyle(context, R.raw.custom_map))
    }

    override fun onMarkerClick(p0: Marker?): Boolean {
        findNavController().navigate(R.id.ubicationDetailFragmentDialog)
        return true
    }

}
